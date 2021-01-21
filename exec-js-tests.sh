#!/bin/bash
./launcher.main.kts --compileAll --target=JS
./launcher.main.kts --run --mainClass=Binary_Test_Suite --partitionMode=Off --target=JS > x 2>&1
grep --text "File(resources/binary//config2).print(" x | sed "s-File(resources/binary//config2).print(--g" > resources/binary/config3
diff resources/binary/config resources/binary/config3 -y | grep "|"
