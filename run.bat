@echo off
setlocal

set _JAVA_AWT_WM_NONREPARENTING=1

cd /d "%~dp0src"
if errorlevel 1 (
    echo Failed to enter "%~dp0src"
    exit /b 1
)

    javac *.java
    if errorlevel 1 (
        echo Compilation failed.
        exit /b 1
    )

        java Activator
        exit /b %errorlevel%
