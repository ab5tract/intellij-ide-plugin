:<<"::CMDLITERAL"
@ECHO OFF
GOTO :CMDSCRIPT
::CMDLITERAL

set -eux
root="$(cd "$(dirname "$0")/.."; pwd)"
exec "$root/platform/jps-bootstrap/jps-bootstrap.sh" "$@" "$root" edument.raku.comma.build CommaCommunityInstallersBuildTarget
:CMDSCRIPT

call "%~dp0\..\platform\jps-bootstrap\jps-bootstrap.cmd" %* edument.raku.comma.build CommaCommunityInstallersBuildTarget
EXIT /B %ERRORLEVEL%
