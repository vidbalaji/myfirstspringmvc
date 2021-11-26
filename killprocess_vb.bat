
@echo on


for /L %%n in (1,1,100) do ( 
ping -n 10 127.0.0.1>nul
for /F "tokens=1" %%i in (D:\processget\src\test.txt) do (

	
	TASKKILL /F /IM %%i 
	echo %%n

)
)