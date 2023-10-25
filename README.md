# AWS Kendra Demo
Welcome to the AWS Kendra Demo. Here, you'll find an example of how to use the AWS Kendra with Spring Boot that provides for interacting with its services.

For more information on getting started with AWS Kendra, see on [What is Amazon Kendra?](https://docs.aws.amazon.com/kendra/latest/dg/what-is-kendra.html).



# Prerequisite
To use the examples in this documentation, you must have an AWS account and create an AWS Kendra Index.

In the application.yml, you should enter the access key, secret key, and the index ID generated in AWS Kendra.


# Run Guide
1. To check if you have Java version 11 installed, run the following command in your terminal: `java -version`. 
2. Build the project: `./gradlew build`.
3. Build the project into a JAR file: `java -jar aws-kendra-demo-0.0.1-SNAPSHOT.jar`.

# API's
- Search Query: `Get`, `/search`
- Create Index: `Post`, `/kendra/index`

# Query Result Examples
```json
[
  {
    "type": "ANSWER",
    "title": "OperatingSystems_18.mp4.txt",
    "excerpt": "[281.12 ~ 284.44] Now the context is represented in the PCB of the process.[284.45 ~ 289.44] Now PCB stands for process control block which we studied in the previous lecture and we [289.44 ~ 294.07] saw what are the things we have in a process control block that represents a particular process.[294",
    "uri": "https://my-s3-bucket/videos/OperatingSystems_18.mp4.txt"
  },
  {
    "type": "QUESTION_ANSWER",
    "title": "",
    "excerpt": "It contains Process Number (PID), Process state , Process scheduling state, Process structuring information, Interprocess communication information, Program Counter (PC), CPU Registers, CPU Scheduling Information,Memory Management Information,  Accounting Information",
    "uri": "https://en.wikipedia.org/wiki/Process_control_block"
  },
  {
    "type": "DOCUMENT",
    "title": "OperatingSystems_18.mp4.txt",
    "excerpt": "...281.12 ~ 284.44] Now the context is represented in the PCB of the process.[284.45 ~ 289.44] Now PCB stands for process control block which we studied in the previous lecture and we [289.44 ~ 294.07] saw what are the things we have in a process...",
    "uri": "https://my-s3-bucket/videos/OperatingSystems_18.mp4.txt"
  },
  {
    "type": "DOCUMENT",
    "title": "OperatingSystems_37.mp4.txt",
    "excerpt": "...a first come,[236.74 ~ 238.19] first served basis.[238.5 ~ 242.24] So what happens is when a process enters the ridicu,[242.25 ~ 245.57] it's PCB is linked onto the tail of the Q.[245.58 ~ 249.12] So we have studied about the different state that the process can be in.[249.3 ~ 252.69...",
    "uri": "https://my-s3-bucket/videos/OperatingSystems_37.mp4.txt"
  },
  {
    "type": "DOCUMENT",
    "title": "OperatingSystems_17.mp4.txt",
    "excerpt": "...concerned.[11.67 ~ 16.3] So it says each process is represented in the operating system by a [16.3 ~ 19.43] process controlled blog abbreviated as PCB,[19.7 ~ 22.27] and it is also called a task control blog...",
    "uri": "https://my-s3-bucket/videos/OperatingSystems_17.mp4.txt"
  }
]
```