use MONKEY-SEE-NO-EVAL;

constant TEST_HARNESS_PREFIX = 'TEST_HARNESS_PREFIX';

sub populate-test-files-by-dir(@test-files, $start, :@patterns) {
    my @todo = $start.IO;
    while @todo {
        for @todo.pop.dir(test => { so (not $_.Str eq '.' | '..') }) {
            if .IO.d {
                @todo.push($_);
            }
            elsif .IO.extension eq 't' | 't6' | 'rakutest' {
                if @patterns {
                    for @patterns -> $pattern {
                        @test-files.push($_) if $_ ~~ $pattern;
                    }
                }
                else {
                    @test-files.push($_);
                }
            };
        }
    }
}

sub compile-pattern($pattern-line) {
    my @patterns;
    for $pattern-line.split(';') -> $pattern {
        my $regex = "'" ~ $pattern.split('*').join("' .*? '") ~ "'";
        @patterns.push: EVAL '/' ~ $regex ~ '/';
    }
    @patterns;
}

sub populate-test-files(@test-files, @paths, :$pattern) {
    for @paths -> $path {
        if $path.IO.d {
            populate-test-files-by-dir(@test-files, $path, patterns => $pattern ?? compile-pattern($pattern) !! ());
        } else {
            @test-files.push($path);
        }
    }
}

sub MAIN(:@paths, :$pattern, :@args) {
    # Gather and sort test files.
    my @test-files;
    populate-test-files(@test-files, @paths, :$pattern);
    @test-files .= sort;

    # Check if we need to write coverage data.
    my $coverage-dir;
    with %*ENV<COMMA_TEST_COVERAGE> {
        $coverage-dir = .IO;
        $coverage-dir.mkdir;
    }
    my %coverage-index;

    react {
        my $jobs = %*ENV<TEST_JOBS> // $*KERNEL.cpu-cores;
        run-a-test-file for ^$jobs;

        sub run-a-test-file {
            with @test-files.shift -> $file {
                my $proc = Proc::Async.new($*EXECUTABLE, @args, $file);
                my $output;
                whenever $proc {
                    $output ~= $_;
                }

                my $ENV;
                my $cov-name;
                my $cov-file;
                if $coverage-dir {
                    $cov-name = $file.subst(/\W+/, '-', :g);
                    $cov-file = $coverage-dir.add($cov-name);
                    $ENV := {
                        %*ENV,
                        MVM_SPESH_DISABLE => '1',
                        MVM_COVERAGE_LOG => $cov-file ~ '.%d'
                    };
                }
                else {
                    $ENV := %*ENV;
                }

                my $pid;
                whenever $proc.ready {
                    $cov-file ~= ".$_" if $cov-file;
                }

                whenever $proc.start(:$ENV) -> $exit {
                    say $output;
                    say "{ TEST_HARNESS_PREFIX } file $file";
                    try $*OUT.flush;
                    if $cov-file && $cov-file.IO.e {
                        %coverage-index{$file} = $cov-file;
                    }
                    elsif $cov-file && dir($coverage-dir, :test(/^$cov-name/)) -> @found {
                        %coverage-index{$file} = @found[0].absolute;
                    }
                    run-a-test-file;
                }
            }
        }
    }

    if %coverage-index {
        $coverage-dir.add('index').spurt: %coverage-index.fmt("%s\t%s");
    }
}