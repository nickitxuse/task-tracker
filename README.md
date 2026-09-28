#TASK TRACKER V 0.1

LETS YOU TRACK YOUR TASKS TO IMPROVE YOUR TIME-MANAGEMENT AND ALSO IF YOU HAVE A LOT OF THINGS TO DO. WORKS WITH HARD AND EASY TASKS.

-----------------------------------------------------------------------------------------------------------------------------------------------
##FEATURES
1. CREATE TASKS.
2. CONTROL THEIR COMPLETION.
3. DELETE TASKS.
4. MANAGE YOUR TASK BOARD AS YOU WISH.


##REQUIREMENTS
- JAVA ver. 21
- Maven
- MySQL

##SETUP
1. Create MySQL DB by using schema.sql
2. Create $env:DB_URL $env:DB_USER $env:DB_PASSWORD in your powershell terminal. 
    $env:DB_URL = "jdbc url for DB"
    $env:DB_USER = "USER NAME"
    $env:DB_PASSWORD = Read-Host "Пароль MySQL" -MaskInput
3. Run Main through Maven: mvn compile exec:java "-Dexec.mainClass=com.practice.Main"
