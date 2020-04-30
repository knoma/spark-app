## Build and Demo process

### Clone the Repo
`git clone https://github.com/knoma/spark-app`

### Build
`./gradlew clean build`
### Run
`./gradlew run`
### All Together
`./gradlew clean run`


### Run in IDEA 
Create new run task with VM option -Dspark.master=local[*]

### Run in spark-submit
``` 
spark-submit \
   --class spark.pi.app.Cass \
   --master "local[*]" \
   ./build/libs/spark-app.jar \ 
``` 




