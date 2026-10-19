#!/bin/sh
export _JAVA_AWT_WM_NONREPARENTING=1
SCRIPT_DIR=$(dirname "$(readlink -f "$0")")

# 1. Переходим в папку с исходниками
cd "$SCRIPT_DIR/src" || exit 1

# 2. Компилируем все файлы (класс-файлы создадутся прямо там)
javac *.java

# 3. Запускаем главный класс Activator
exec java Activator
