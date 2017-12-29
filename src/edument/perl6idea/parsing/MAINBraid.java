package edument.perl6idea.parsing;

public class MAINBraid extends Cursor<MAINBraid> {

    public int runRule() {
        int result;
        switch (this.ruleNumber) {
        case 0:
            result = this._0_TOP();
            break;
        case 1:
            result = this._1_apostrophe();
            break;
        case 2:
            result = this._2_ident();
            break;
        case 3:
            result = this._3_identifier();
            break;
        case 4:
            result = this._4_name();
            break;
        case 5:
            result = this._5_morename();
            break;
        case 6:
            result = this._6_longname();
            break;
        case 7:
            result = this._7_module_name();
            break;
        case 8:
            result = this._8_end_keyword();
            break;
        case 9:
            result = this._9_ws();
            break;
        case 10:
            result = this._10_unsp();
            break;
        case 11:
            result = this._11_vws();
            break;
        case 12:
            result = this._12_unv();
            break;
        case 13:
            result = this._13_comment();
            break;
        case 14:
            result = this._14_statementlist();
            break;
        case 15:
            result = this._15_statement();
            break;
        case 16:
            result = this._16_bogus_statement();
            break;
        case 17:
            result = this._17_statement_control();
            break;
        case 18:
            result = this._18_statement_control_use();
            break;
        case 19:
            result = this._19_term();
            break;
        case 20:
            result = this._20_variable();
            break;
        case 21:
            result = this._21_scope_declarator();
            break;
        case 22:
            result = this._22_declarator();
            break;
        case 23:
            result = this._23_variable_declarator();
            break;
        case 24:
            result = this._24_initializer();
            break;
        case 25:
            result = this._25_sigil();
            break;
        case 26:
            result = this._26_twigil();
            break;
        case 27:
            result = this._27_desigilname();
            break;
        case 28:
            result = this._28_value();
            break;
        case 29:
            result = this._29_number();
            break;
        case 30:
            result = this._30_numish();
            break;
        case 31:
            result = this._31_dec_number();
            break;
        case 32:
            result = this._32_escale();
            break;
        case 33:
            result = this._33_sign();
            break;
        case 34:
            result = this._34_integer();
            break;
        case 35:
            result = this._35_decint();
            break;
        case 36:
            result = this._36_hexint();
            break;
        case 37:
            result = this._37_octint();
            break;
        case 38:
            result = this._38_binint();
            break;
        case 39:
            result = this._39_EXPR();
            break;
        case 40:
            result = this._40_prefixish();
            break;
        case 41:
            result = this._41_prefix();
            break;
        case 42:
            result = this._42_postfixish();
            break;
        case 43:
            result = this._43_postfix();
            break;
        case 44:
            result = this._44_infixish();
            break;
        case 45:
            result = this._45_infix();
            break;
        case 46:
            result = this._46_termish();
            break;
        case 47:
            result = this.___lookahead_0();
            break;
        case 48:
            result = this.___lookahead_1();
            break;
        case 49:
            result = this.___lookahead_2();
            break;
        default:
            throw new RuntimeException();

        }
        return result;
    }

    private int _0_TOP() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 14;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _1_apostrophe() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.inCharList("'-"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int _2_ident() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(2);
                this.bsMark(1);
                if (!(this.alphaChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(2);
                this.state = 2;
                continue;

            case 1:
                if (!(this.literal("_"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 2;
                continue;

            case 2:
                this.bsMark(4);
                this.state = 3;
                break;
            case 3:
                if (!(this.wordChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(4);
                ++rep;
                this.bsCommit(4);
                this.bsMark(4, rep);
                this.state = 3;
                continue;

            case 4:
                return -1;

            }
        }
    }

    private int _3_identifier() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 2;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(5);
                this.state = 2;
                break;
            case 2:
                this.state = 3;
                return 1;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 4;
                return 2;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(5);
                ++rep;
                this.bsCommit(5);
                this.bsMark(5, rep);
                this.state = 2;
                continue;

            case 5:
                return -1;

            }
        }
    }

    private int _4_name() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(9);
                this.bsMark(5);
                this.state = 1;
                return 3;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(4);
                this.state = 2;
                break;
            case 2:
                this.state = 3;
                return 5;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(4);
                ++rep;
                this.bsCommit(4);
                this.bsMark(4, rep);
                this.state = 2;
                continue;

            case 4:
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 5:
                this.bsFailMark(8);
                this.state = 6;
                break;
            case 6:
                this.state = 7;
                return 5;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(8);
                ++rep;
                this.bsCommit(8);
                this.bsMark(8, rep);
                this.state = 6;
                continue;

            case 8:
                this.state = 9;
                continue;

            case 9:
                return -1;

            }
        }
    }

    private int _5_morename() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.literal("::"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsMark(3);
                this.state = 1;
                break;
            case 1:
                this.state = 2;
                return 3;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(3);
                this.state = 3;
                continue;

            case 3:
                return -1;

            }
        }
    }

    private int _6_longname() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 4;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _7_module_name() {
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.NAME);
                this.state = 1;
                return 6;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 2;
                return -3;

            case 2:
                return -1;

            }
        }
    }

    private int _8_end_keyword() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.rightWordBoundary())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int _9_ws() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                if (this.lookahead(47)) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsMark(10);
                this.state = 1;
                break;
            case 1:
                this.bsFailMark(9);
                this.bsMark(5);
                this.startToken(Perl6TokenTypes.WHITE_SPACE);
                this.bsFailMark(3);
                this.bsMark(2);
                if (!(this.inCharList("\r"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                if (!(this.newlineChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(3);
                this.state = 3;
                continue;

            case 2:
                if (!(this.inCharList("\n                \r\u0085\u2028\u2029\r\n"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 3;
                continue;

            case 3:
                this.state = 4;
                return -3;

            case 4:
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 5:
                this.bsMark(7);
                this.state = 6;
                return 12;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 7:
                this.state = 8;
                return 10;

            case 8:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 9;
                continue;

            case 9:
                rep = this.peekRep(10);
                ++rep;
                this.bsCommit(10);
                this.bsMark(10, rep);
                this.state = 1;
                continue;

            case 10:
                return -1;

            }
        }
    }

    private int _10_unsp() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.WHITE_SPACE);
                if (!(this.literal("\\"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                if (!(this.lookahead(48))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                this.bsMark(9);
                this.state = 2;
                break;
            case 2:
                this.bsFailMark(8);
                this.bsMark(4);
                this.state = 3;
                return 11;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 4:
                this.bsMark(6);
                this.state = 5;
                return 12;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 6:
                this.state = 7;
                return 10;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 8;
                continue;

            case 8:
                rep = this.peekRep(9);
                ++rep;
                this.bsCommit(9);
                this.bsMark(9, rep);
                this.state = 2;
                continue;

            case 9:
                return -1;

            }
        }
    }

    private int _11_vws() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.WHITE_SPACE);
                this.bsFailMark(2);
                this.state = 1;
                break;
            case 1:
                if (!(this.inCharList("\n                \r\u0085\u2028\u2029\r\n"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(2);
                ++rep;
                this.bsCommit(2);
                this.bsMark(2, rep);
                this.state = 1;
                continue;

            case 2:
                this.state = 3;
                return -3;

            case 3:
                return -1;

            }
        }
    }

    private int _12_unv() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(9);
                this.bsMark(2);
                this.state = 1;
                return 13;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 2:
                this.startToken(Perl6TokenTypes.WHITE_SPACE);
                this.bsFailMark(4);
                this.state = 3;
                break;
            case 3:
                if (!(this.inCharList("	 \u00A0\u1680\u180E\u2002\u2003\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(4);
                ++rep;
                this.bsCommit(4);
                this.bsMark(4, rep);
                this.state = 3;
                continue;

            case 4:
                this.state = 5;
                return -3;

            case 5:
                this.bsMark(8);
                this.state = 6;
                break;
            case 6:
                this.state = 7;
                return 13;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 8:
                this.state = 9;
                continue;

            case 9:
                return -1;

            }
        }
    }

    private int _13_comment() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.COMMENT);
                if (!(this.literal("#"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsMark(2);
                this.state = 1;
                break;
            case 1:
                if (!(this.notNewlineChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(2);
                ++rep;
                this.bsCommit(2);
                this.bsMark(2, rep);
                this.state = 1;
                continue;

            case 2:
                this.state = 3;
                return -3;

            case 3:
                return -1;

            }
        }
    }

    private int _14_statementlist() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.bsMark(3);
                this.state = 1;
                break;
            case 1:
                this.state = 2;
                return 15;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(3);
                ++rep;
                this.bsCommit(3);
                this.bsMark(3, rep);
                this.state = 1;
                continue;

            case 3:
                return -1;

            }
        }
    }

    private int _15_statement() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 9;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsFailMark(7);
                this.bsMark(3);
                this.state = 2;
                return 17;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(7);
                this.state = 7;
                continue;

            case 3:
                this.bsMark(5);
                this.state = 4;
                return 39;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(7);
                this.state = 7;
                continue;

            case 5:
                this.state = 6;
                return 16;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 7;
                continue;

            case 7:
                this.state = 8;
                return 9;

            case 8:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.startToken(Perl6TokenTypes.STATEMENT_TERMINATOR);
                this.bsMark(10);
                this.state = 9;
                break;
            case 9:
                if (!(this.literal(";"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 10:
                this.state = 11;
                return -3;

            case 11:
                this.state = 12;
                return 9;

            case 12:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _16_bogus_statement() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.BAD_CHARACTER);
                this.bsFailMark(2);
                this.state = 1;
                break;
            case 1:
                if (!(this.notInCharList(";"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(2);
                ++rep;
                this.bsCommit(2);
                this.bsMark(2, rep);
                this.state = 1;
                continue;

            case 2:
                this.state = 3;
                return -3;

            case 3:
                return -1;

            }
        }
    }

    private int _17_statement_control() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 18;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _18_statement_control_use() {
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.STATEMENT_CONTROL);
                if (!(this.literal("use"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return -3;

            case 1:
                this.state = 2;
                return 9;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 3;
                return 7;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _19_term() {
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(6);
                this.bsMark(2);
                this.state = 1;
                return 20;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(6);
                this.state = 6;
                continue;

            case 2:
                this.bsMark(4);
                this.state = 3;
                return 21;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(6);
                this.state = 6;
                continue;

            case 4:
                this.state = 5;
                return 28;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 6;
                continue;

            case 6:
                return -1;

            }
        }
    }

    private int _20_variable() {
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.VARIABLE);
                this.state = 1;
                return 25;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(4);
                this.state = 2;
                break;
            case 2:
                this.state = 3;
                return 26;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(4);
                this.state = 4;
                continue;

            case 4:
                this.state = 5;
                return 27;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 6;
                return -3;

            case 6:
                return -1;

            }
        }
    }

    private int _21_scope_declarator() {
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.SCOPE_DECLARATOR);
                this.bsFailMark(9);
                this.bsMark(1);
                if (!(this.literal("my"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 1:
                this.bsMark(2);
                if (!(this.literal("our"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 2:
                this.bsMark(3);
                if (!(this.literal("has"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 3:
                this.bsMark(4);
                if (!(this.literal("HAS"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 4:
                this.bsMark(5);
                if (!(this.literal("augment"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 5:
                this.bsMark(6);
                if (!(this.literal("anon"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 6:
                this.bsMark(7);
                if (!(this.literal("state"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 7:
                this.bsMark(8);
                if (!(this.literal("supersede"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 8:
                if (!(this.literal("unit"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 9;
                continue;

            case 9:
                this.state = 10;
                return 8;

            case 10:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 11;
                return -3;

            case 11:
                this.state = 12;
                return 9;

            case 12:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsFailMark(15);
                this.bsMark(14);
                this.state = 13;
                return 22;

            case 13:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(15);
                this.state = 15;
                continue;

            case 14:
                this.state = 15;
                continue;

            case 15:
                return -1;

            }
        }
    }

    private int _22_declarator() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 23;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(5);
                this.state = 2;
                break;
            case 2:
                this.state = 3;
                return 9;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 4;
                return 24;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(5);
                this.state = 5;
                continue;

            case 5:
                return -1;

            }
        }
    }

    private int _23_variable_declarator() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 20;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _24_initializer() {
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.INFIX);
                this.bsFailMark(3);
                this.bsMark(1);
                if (!(this.literal("="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(3);
                this.state = 3;
                continue;

            case 1:
                this.bsMark(2);
                if (!(this.literal(":="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(3);
                this.state = 3;
                continue;

            case 2:
                if (!(this.literal("::="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 3;
                continue;

            case 3:
                this.state = 4;
                return -3;

            case 4:
                this.state = 5;
                return 9;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(8);
                this.state = 6;
                break;
            case 6:
                this.state = 7;
                return 39;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(8);
                this.state = 8;
                continue;

            case 8:
                return -1;

            }
        }
    }

    private int _25_sigil() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.inCharList("$@%&"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int _26_twigil() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.inCharList(".!^:*?=~"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                if (!(this.lookahead(49))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int _27_desigilname() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 6;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _28_value() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 29;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _29_number() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 30;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _30_numish() {
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(9);
                this.bsMark(2);
                this.state = 1;
                return 31;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 2:
                this.bsMark(4);
                this.state = 3;
                return 34;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(9);
                this.state = 9;
                continue;

            case 4:
                this.startToken(Perl6TokenTypes.NUMBER_LITERAL);
                this.bsFailMark(7);
                this.bsMark(5);
                if (!(this.literal("\u221E"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(7);
                this.state = 7;
                continue;

            case 5:
                this.bsMark(6);
                if (!(this.literal("NaN"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                if (!(this.rightWordBoundary())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(7);
                this.state = 7;
                continue;

            case 6:
                if (!(this.literal("Inf"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                if (!(this.rightWordBoundary())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 7;
                continue;

            case 7:
                this.state = 8;
                return -3;

            case 8:
                this.state = 9;
                continue;

            case 9:
                return -1;

            }
        }
    }

    private int _31_dec_number() {
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(19);
                this.bsMark(12);
                this.startToken(Perl6TokenTypes.NUMBER_LITERAL);
                this.bsFailMark(10);
                this.bsMark(3);
                if (!(this.literal("."))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return 35;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 2;
                return 32;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 3:
                this.bsMark(7);
                this.state = 4;
                return 35;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                if (!(this.literal("."))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 5;
                return 35;

            case 5:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 6;
                return 32;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 7:
                this.state = 8;
                return 35;

            case 8:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 9;
                return 32;

            case 9:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 10;
                continue;

            case 10:
                this.state = 11;
                return -3;

            case 11:
                this.bsCommit(19);
                this.state = 19;
                continue;

            case 12:
                this.startToken(Perl6TokenTypes.RAT_LITERAL);
                this.bsFailMark(17);
                this.bsMark(14);
                if (!(this.literal("."))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 13;
                return 35;

            case 13:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(17);
                this.state = 17;
                continue;

            case 14:
                this.state = 15;
                return 35;

            case 15:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                if (!(this.literal("."))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 16;
                return 35;

            case 16:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 17;
                continue;

            case 17:
                this.state = 18;
                return -3;

            case 18:
                this.state = 19;
                continue;

            case 19:
                return -1;

            }
        }
    }

    private int _32_escale() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.inCharList("Ee"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                return 33;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 2;
                return 35;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _33_sign() {
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(4);
                this.bsMark(1);
                if (!(this.literal("+"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(4);
                this.state = 4;
                continue;

            case 1:
                this.bsMark(2);
                if (!(this.literal("-"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(4);
                this.state = 4;
                continue;

            case 2:
                this.bsMark(3);
                if (!(this.literal("\u2212"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(4);
                this.state = 4;
                continue;

            case 3:
                if (!(this.literal(""))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 4;
                continue;

            case 4:
                return -1;

            }
        }
    }

    private int _34_integer() {
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.INTEGER_LITERAL);
                this.bsFailMark(21);
                this.bsMark(19);
                if (!(this.literal("0"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsFailMark(18);
                this.bsMark(4);
                if (!(this.literal("b"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsMark(2);
                this.state = 1;
                break;
            case 1:
                if (!(this.literal("_"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(2);
                this.state = 2;
                continue;

            case 2:
                this.state = 3;
                return 38;

            case 3:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(18);
                this.state = 18;
                continue;

            case 4:
                this.bsMark(8);
                if (!(this.literal("o"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsMark(6);
                this.state = 5;
                break;
            case 5:
                if (!(this.literal("_"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(6);
                this.state = 6;
                continue;

            case 6:
                this.state = 7;
                return 37;

            case 7:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(18);
                this.state = 18;
                continue;

            case 8:
                this.bsMark(12);
                if (!(this.literal("x"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsMark(10);
                this.state = 9;
                break;
            case 9:
                if (!(this.literal("_"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 10:
                this.state = 11;
                return 36;

            case 11:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(18);
                this.state = 18;
                continue;

            case 12:
                this.bsMark(16);
                if (!(this.literal("d"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsMark(14);
                this.state = 13;
                break;
            case 13:
                if (!(this.literal("_"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(14);
                this.state = 14;
                continue;

            case 14:
                this.state = 15;
                return 35;

            case 15:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsCommit(18);
                this.state = 18;
                continue;

            case 16:
                this.state = 17;
                return 35;

            case 17:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 18;
                continue;

            case 18:
                this.bsCommit(21);
                this.state = 21;
                continue;

            case 19:
                this.state = 20;
                return 35;

            case 20:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 21;
                continue;

            case 21:
                this.state = 22;
                return -3;

            case 22:
                return -1;

            }
        }
    }

    private int _35_decint() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(5);
                this.state = 1;
                break;
            case 1:
                this.bsFailMark(3);
                this.state = 2;
                break;
            case 2:
                if (!(this.digitChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(3);
                ++rep;
                this.bsCommit(3);
                this.bsMark(3, rep);
                this.state = 2;
                continue;

            case 3:
                rep = this.peekRep(5);
                ++rep;
                this.bsCommit(5);
                this.bsMark(5, rep);
                this.state = 4;
                continue;

            case 4:
                if (!(this.literal("_"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                continue;

            case 5:
                return -1;

            }
        }
    }

    private int _36_hexint() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(7);
                this.state = 1;
                break;
            case 1:
                this.bsFailMark(5);
                this.state = 2;
                break;
            case 2:
                this.bsFailMark(4);
                this.bsMark(3);
                if (!(this.digitChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(4);
                this.state = 4;
                continue;

            case 3:
                if (!(this.inCharList("abcdefABCDEF\uFF41\uFF42\uFF43\uFF44\uFF45\uFF46\uFF21\uFF22\uFF23\uFF24\uFF25\uFF26"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 4;
                continue;

            case 4:
                rep = this.peekRep(5);
                ++rep;
                this.bsCommit(5);
                this.bsMark(5, rep);
                this.state = 2;
                continue;

            case 5:
                rep = this.peekRep(7);
                ++rep;
                this.bsCommit(7);
                this.bsMark(7, rep);
                this.state = 6;
                continue;

            case 6:
                if (!(this.literal("_"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                continue;

            case 7:
                return -1;

            }
        }
    }

    private int _37_octint() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(5);
                this.state = 1;
                break;
            case 1:
                this.bsFailMark(3);
                this.state = 2;
                break;
            case 2:
                if (!(this.digitChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(3);
                ++rep;
                this.bsCommit(3);
                this.bsMark(3, rep);
                this.state = 2;
                continue;

            case 3:
                rep = this.peekRep(5);
                ++rep;
                this.bsCommit(5);
                this.bsMark(5, rep);
                this.state = 4;
                continue;

            case 4:
                if (!(this.literal("_"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                continue;

            case 5:
                return -1;

            }
        }
    }

    private int _38_binint() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(5);
                this.state = 1;
                break;
            case 1:
                this.bsFailMark(3);
                this.state = 2;
                break;
            case 2:
                if (!(this.digitChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(3);
                ++rep;
                this.bsCommit(3);
                this.bsMark(3, rep);
                this.state = 2;
                continue;

            case 3:
                rep = this.peekRep(5);
                ++rep;
                this.bsCommit(5);
                this.bsMark(5, rep);
                this.state = 4;
                continue;

            case 4:
                if (!(this.literal("_"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 1;
                continue;

            case 5:
                return -1;

            }
        }
    }

    private int _39_EXPR() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.bsMark(3);
                this.state = 1;
                break;
            case 1:
                this.state = 2;
                return 40;

            case 2:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(3);
                ++rep;
                this.bsCommit(3);
                this.bsMark(3, rep);
                this.state = 1;
                continue;

            case 3:
                this.state = 4;
                return 46;

            case 4:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(7);
                this.state = 5;
                break;
            case 5:
                this.state = 6;
                return 42;

            case 6:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(7);
                ++rep;
                this.bsCommit(7);
                this.bsMark(7, rep);
                this.state = 5;
                continue;

            case 7:
                this.state = 8;
                return 9;

            case 8:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(22);
                this.state = 9;
                break;
            case 9:
                this.state = 10;
                return 44;

            case 10:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.state = 11;
                return 9;

            case 11:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(20);
                this.state = 12;
                break;
            case 12:
                this.bsMark(15);
                this.state = 13;
                break;
            case 13:
                this.state = 14;
                return 40;

            case 14:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(15);
                ++rep;
                this.bsCommit(15);
                this.bsMark(15, rep);
                this.state = 13;
                continue;

            case 15:
                this.state = 16;
                return 46;

            case 16:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                this.bsMark(19);
                this.state = 17;
                break;
            case 17:
                this.state = 18;
                return 42;

            case 18:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(19);
                ++rep;
                this.bsCommit(19);
                this.bsMark(19, rep);
                this.state = 17;
                continue;

            case 19:
                this.bsCommit(20);
                this.state = 20;
                continue;

            case 20:
                this.state = 21;
                return 9;

            case 21:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                rep = this.peekRep(22);
                ++rep;
                this.bsCommit(22);
                this.bsMark(22, rep);
                this.state = 9;
                continue;

            case 22:
                return -1;

            }
        }
    }

    private int _40_prefixish() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 41;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _41_prefix() {
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.PREFIX);
                this.bsFailMark(16);
                this.bsMark(1);
                if (!(this.literal("++\u269B"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 1:
                this.bsMark(2);
                if (!(this.literal("--\u269B"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 2:
                this.bsMark(3);
                if (!(this.literal("++"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 3:
                this.bsMark(4);
                if (!(this.literal("--"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 4:
                this.bsMark(5);
                if (!(this.literal("+^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 5:
                this.bsMark(6);
                if (!(this.literal("~^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 6:
                this.bsMark(7);
                if (!(this.literal("?^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 7:
                this.bsMark(8);
                if (!(this.literal("+"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 8:
                this.bsMark(9);
                if (!(this.literal("~"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 9:
                this.bsMark(10);
                if (!(this.literal("-"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 10:
                this.bsMark(11);
                if (!(this.literal("\u2212"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 11:
                this.bsMark(12);
                if (!(this.literal("?"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 12:
                this.bsMark(13);
                if (!(this.literal("!"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 13:
                this.bsMark(14);
                if (!(this.literal("|"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 14:
                this.bsMark(15);
                if (!(this.literal("^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(16);
                this.state = 16;
                continue;

            case 15:
                if (!(this.literal("\u269B"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 16;
                continue;

            case 16:
                this.state = 17;
                return -3;

            case 17:
                return -1;

            }
        }
    }

    private int _42_postfixish() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 43;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _43_postfix() {
        int rep;
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.POSTFIX);
                this.bsFailMark(10);
                this.bsMark(1);
                if (!(this.literal("i"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 1:
                this.bsMark(2);
                if (!(this.literal("\u269B++"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 2:
                this.bsMark(3);
                if (!(this.literal("\u269B--"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 3:
                this.bsMark(4);
                if (!(this.literal("++"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 4:
                this.bsMark(5);
                if (!(this.literal("--"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(10);
                this.state = 10;
                continue;

            case 5:
                this.bsMark(7);
                this.state = 6;
                break;
            case 6:
                if (!(this.inCharList("\u207B\u207A\u00AF"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(7);
                this.state = 7;
                continue;

            case 7:
                this.bsFailMark(9);
                this.state = 8;
                break;
            case 8:
                if (!(this.inCharList("\u2070\u00B9\u00B2\u00B3\u2074\u2075\u2076\u2077\u2078\u2079"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                rep = this.peekRep(9);
                ++rep;
                this.bsCommit(9);
                this.bsMark(9, rep);
                this.state = 8;
                continue;

            case 9:
                this.state = 10;
                continue;

            case 10:
                this.state = 11;
                return -3;

            case 11:
                return -1;

            }
        }
    }

    private int _44_infixish() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 45;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int _45_infix() {
        while (true) {
            switch (this.state) {
            case 0:
                this.startToken(Perl6TokenTypes.INFIX);
                this.bsFailMark(144);
                this.bsMark(1);
                if (!(this.literal("notandthen"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 1:
                this.bsMark(2);
                if (!(this.literal("andthen"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 2:
                this.bsMark(3);
                if (!(this.literal("(elem)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 3:
                this.bsMark(4);
                if (!(this.literal("(cont)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 4:
                this.bsMark(5);
                if (!(this.literal("orelse"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 5:
                this.bsMark(6);
                if (!(this.literal("unicmp"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 6:
                this.bsMark(7);
                if (!(this.literal("minmax"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 7:
                this.bsMark(8);
                if (!(this.literal("before"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 8:
                this.bsMark(9);
                if (!(this.literal("after"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 9:
                this.bsMark(10);
                if (!(this.literal("^fff^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 10:
                this.bsMark(11);
                if (!(this.literal("...^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 11:
                this.bsMark(12);
                if (!(this.literal("^ff^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 12:
                this.bsMark(13);
                if (!(this.literal("^fff"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 13:
                this.bsMark(14);
                if (!(this.literal("fff^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 14:
                this.bsMark(15);
                if (!(this.literal("<<=="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 15:
                this.bsMark(16);
                if (!(this.literal("==>>"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 16:
                this.bsMark(17);
                if (!(this.literal("^..^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 17:
                this.bsMark(18);
                if (!(this.literal("coll"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 18:
                this.bsMark(19);
                if (!(this.literal("does"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 19:
                this.bsMark(20);
                if (!(this.literal("div"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 20:
                this.bsMark(21);
                if (!(this.literal("gcd"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 21:
                this.bsMark(22);
                if (!(this.literal("lcm"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 22:
                this.bsMark(23);
                if (!(this.literal("mod"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 23:
                this.bsMark(24);
                if (!(this.literal("(&)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 24:
                this.bsMark(25);
                if (!(this.literal("(.)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 25:
                this.bsMark(26);
                if (!(this.literal("(|)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 26:
                this.bsMark(27);
                if (!(this.literal("(^)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 27:
                this.bsMark(28);
                if (!(this.literal("(+)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 28:
                this.bsMark(29);
                if (!(this.literal("(-)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 29:
                this.bsMark(30);
                if (!(this.literal("=~="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 30:
                this.bsMark(31);
                if (!(this.literal("=:="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 31:
                this.bsMark(32);
                if (!(this.literal("==="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 32:
                this.bsMark(33);
                if (!(this.literal("eqv"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 33:
                this.bsMark(34);
                if (!(this.literal("!~~"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 34:
                this.bsMark(35);
                if (!(this.literal("(<)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 35:
                this.bsMark(36);
                if (!(this.literal("(>)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 36:
                this.bsMark(37);
                if (!(this.literal("(<=)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 37:
                this.bsMark(38);
                if (!(this.literal("(>=)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 38:
                this.bsMark(39);
                if (!(this.literal("(<+)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 39:
                this.bsMark(40);
                if (!(this.literal("(>+)"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 40:
                this.bsMark(41);
                if (!(this.literal("min"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 41:
                this.bsMark(42);
                if (!(this.literal("max"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 42:
                this.bsMark(43);
                if (!(this.literal("::="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 43:
                this.bsMark(44);
                if (!(this.literal("..."))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 44:
                this.bsMark(45);
                if (!(this.literal("^ff"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 45:
                this.bsMark(46);
                if (!(this.literal("ff^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 46:
                this.bsMark(47);
                if (!(this.literal("fff"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 47:
                this.bsMark(48);
                if (!(this.literal("\u269B+="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 48:
                this.bsMark(49);
                if (!(this.literal("\u269B-="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 49:
                this.bsMark(50);
                if (!(this.literal("\u269B\u2212="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 50:
                this.bsMark(51);
                if (!(this.literal("and"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 51:
                this.bsMark(52);
                if (!(this.literal("xor"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 52:
                this.bsMark(53);
                if (!(this.literal("<=="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 53:
                this.bsMark(54);
                if (!(this.literal("==>"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 54:
                this.bsMark(55);
                if (!(this.literal("^.."))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 55:
                this.bsMark(56);
                if (!(this.literal("..^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 56:
                this.bsMark(57);
                if (!(this.literal("leg"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 57:
                this.bsMark(58);
                if (!(this.literal("cmp"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 58:
                this.bsMark(59);
                if (!(this.literal("<=>"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 59:
                this.bsMark(60);
                if (!(this.literal("but"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 60:
                this.bsMark(61);
                if (!(this.literal("**"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 61:
                this.bsMark(62);
                if (!(this.literal("%%"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 62:
                this.bsMark(63);
                if (!(this.literal("+&"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 63:
                this.bsMark(64);
                if (!(this.literal("~&"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 64:
                this.bsMark(65);
                if (!(this.literal("?&"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 65:
                this.bsMark(66);
                if (!(this.literal("+<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 66:
                this.bsMark(67);
                if (!(this.literal("+>"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 67:
                this.bsMark(68);
                if (!(this.literal("~<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 68:
                this.bsMark(69);
                if (!(this.literal("~>"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 69:
                this.bsMark(70);
                if (!(this.literal("+|"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 70:
                this.bsMark(71);
                if (!(this.literal("+^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 71:
                this.bsMark(72);
                if (!(this.literal("~|"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 72:
                this.bsMark(73);
                if (!(this.literal("~^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 73:
                this.bsMark(74);
                if (!(this.literal("?|"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 74:
                this.bsMark(75);
                if (!(this.literal("?^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 75:
                this.bsMark(76);
                if (!(this.literal("xx"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 76:
                this.bsMark(77);
                if (!(this.literal("=="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 77:
                this.bsMark(78);
                if (!(this.literal("!="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 78:
                this.bsMark(79);
                if (!(this.literal("<="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 79:
                this.bsMark(80);
                if (!(this.literal(">="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 80:
                this.bsMark(81);
                if (!(this.literal("eq"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 81:
                this.bsMark(82);
                if (!(this.literal("ne"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 82:
                this.bsMark(83);
                if (!(this.literal("le"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 83:
                this.bsMark(84);
                if (!(this.literal("ge"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 84:
                this.bsMark(85);
                if (!(this.literal("lt"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 85:
                this.bsMark(86);
                if (!(this.literal("gt"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 86:
                this.bsMark(87);
                if (!(this.literal("~~"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 87:
                this.bsMark(88);
                if (!(this.literal("&&"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 88:
                this.bsMark(89);
                if (!(this.literal("||"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 89:
                this.bsMark(90);
                if (!(this.literal("^^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 90:
                this.bsMark(91);
                if (!(this.literal("//"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 91:
                this.bsMark(92);
                if (!(this.literal(":="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 92:
                this.bsMark(93);
                if (!(this.literal(".="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 93:
                this.bsMark(94);
                if (!(this.literal("\u2026^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 94:
                this.bsMark(95);
                if (!(this.literal("ff"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 95:
                this.bsMark(96);
                if (!(this.literal("\u269B="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 96:
                this.bsMark(97);
                if (!(this.literal("or"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 97:
                this.bsMark(98);
                if (!(this.literal(".."))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 98:
                this.bsMark(99);
                if (!(this.literal("*"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 99:
                this.bsMark(100);
                if (!(this.literal("\u00D7"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 100:
                this.bsMark(101);
                if (!(this.literal("/"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 101:
                this.bsMark(102);
                if (!(this.literal("\u00F7"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 102:
                this.bsMark(103);
                if (!(this.literal("%"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 103:
                this.bsMark(104);
                if (!(this.literal("+"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 104:
                this.bsMark(105);
                if (!(this.literal("-"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 105:
                this.bsMark(106);
                if (!(this.literal("\u2212"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 106:
                this.bsMark(107);
                if (!(this.literal("x"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 107:
                this.bsMark(108);
                if (!(this.literal("~"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 108:
                this.bsMark(109);
                if (!(this.literal("\u2218"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 109:
                this.bsMark(110);
                if (!(this.literal("o"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 110:
                this.bsMark(111);
                if (!(this.literal("&"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 111:
                this.bsMark(112);
                if (!(this.literal("\u2229"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 112:
                this.bsMark(113);
                if (!(this.literal("\u228D"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 113:
                this.bsMark(114);
                if (!(this.literal("|"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 114:
                this.bsMark(115);
                if (!(this.literal("^"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 115:
                this.bsMark(116);
                if (!(this.literal("\u222A"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 116:
                this.bsMark(117);
                if (!(this.literal("\u2296"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 117:
                this.bsMark(118);
                if (!(this.literal("\u228E"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 118:
                this.bsMark(119);
                if (!(this.literal("\u2216"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 119:
                this.bsMark(120);
                if (!(this.literal("\u2245"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 120:
                this.bsMark(121);
                if (!(this.literal("\u2260"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 121:
                this.bsMark(122);
                if (!(this.literal("\u2264"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 122:
                this.bsMark(123);
                if (!(this.literal("\u2265"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 123:
                this.bsMark(124);
                if (!(this.literal("<"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 124:
                this.bsMark(125);
                if (!(this.literal(">"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 125:
                this.bsMark(126);
                if (!(this.literal("\u2208"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 126:
                this.bsMark(127);
                if (!(this.literal("\u2209"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 127:
                this.bsMark(128);
                if (!(this.literal("\u220B"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 128:
                this.bsMark(129);
                if (!(this.literal("\u220C"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 129:
                this.bsMark(130);
                if (!(this.literal("\u2282"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 130:
                this.bsMark(131);
                if (!(this.literal("\u2284"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 131:
                this.bsMark(132);
                if (!(this.literal("\u2283"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 132:
                this.bsMark(133);
                if (!(this.literal("\u2285"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 133:
                this.bsMark(134);
                if (!(this.literal("\u2286"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 134:
                this.bsMark(135);
                if (!(this.literal("\u2288"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 135:
                this.bsMark(136);
                if (!(this.literal("\u2287"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 136:
                this.bsMark(137);
                if (!(this.literal("\u2289"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 137:
                this.bsMark(138);
                if (!(this.literal("\u227C"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 138:
                this.bsMark(139);
                if (!(this.literal("\u227D"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 139:
                this.bsMark(140);
                if (!(this.literal(","))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 140:
                this.bsMark(141);
                if (!(this.literal("Z"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 141:
                this.bsMark(142);
                if (!(this.literal("X"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 142:
                this.bsMark(143);
                if (!(this.literal("\u2026"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(144);
                this.state = 144;
                continue;

            case 143:
                if (!(this.literal("="))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 144;
                continue;

            case 144:
                this.state = 145;
                return -3;

            case 145:
                return -1;

            }
        }
    }

    private int _46_termish() {
        while (true) {
            switch (this.state) {
            case 0:
                this.state = 1;
                return 19;

            case 1:
                if (this.lastResult.isFailed()) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                } else {
                    this.pos = this.lastResult.getPos();
                }
                return -1;

            }
        }
    }

    private int ___lookahead_0() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.ww())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

    private int ___lookahead_1() {
        while (true) {
            switch (this.state) {
            case 0:
                this.bsFailMark(2);
                this.bsMark(1);
                if (!(this.spaceChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.bsCommit(2);
                this.state = 2;
                continue;

            case 1:
                if (!(this.literal("#"))) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                this.state = 2;
                continue;

            case 2:
                return -1;

            }
        }
    }

    private int ___lookahead_2() {
        while (true) {
            switch (this.state) {
            case 0:
                if (!(this.wordChar())) {
                    if (this.backtrack()) {
                        continue;
                    } else {
                        return -2;
                    }
                }
                return -1;

            }
        }
    }

}
