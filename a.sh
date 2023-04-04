$(./launcher.main.kts --dryMode=Enable --run --mainClass=Launch_Simulator_Config | grep ^exec | sed "s/exec :: //g")  "JavaBridge" ${scenario}
