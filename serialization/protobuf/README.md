## protobuf概念

protobuf是序列化/反序列化组件，可以按照protobuf语法创建.proto文件，编译该文件，生成不同语言的类，创建该类的对象，将对象序列化/反序列化，从而在不同进程中传输该对象。由于protobuf在序列化/反序列化中采用的编解码协议效率很高，因此网络传输效率很高，同时还能跨语言运行，被广泛使用。

protobuf流程图如下所示：

![protobuf流程](src/main/resources/img/protobuf序列化_反序列化流程.png)

## idea配置

### plugin
在maven中配置protobuf插件，用于编译.proto文件生成Java/Python/C++等语言的类：
```
    <build>
        <extensions>
            <extension>
                <groupId>kr.motd.maven</groupId>
                <artifactId>os-maven-plugin</artifactId>
                <version>1.5.0.Final</version>
            </extension>
        </extensions>
        <plugins>
            <plugin>
                <groupId>org.xolstice.maven.plugins</groupId>
                <artifactId>protobuf-maven-plugin</artifactId>
                <version>0.5.0</version>
                <configuration>
                    <protocArtifact>
                        com.google.protobuf:protoc:3.1.0:exe:${os.detected.classifier}
                    </protocArtifact>
                    <pluginId>grpc-java</pluginId>
                    <pluginArtifact>
                        io.grpc:protoc-gen-grpc-java:1.11.0:exe:${os.detected.classifier}
                    </pluginArtifact>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>compile-custom</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```
如下，可以将.proto文件编译生成如下类型的类文件：

![protobuf插件](src/main/resources/img/protobuf插件.png)

## 依赖

plugin必须配合以下maven依赖，才能生成正确的Java类：

```
    <dependencies>
        <dependency>
            <groupId>com.google.protobuf</groupId>
            <artifactId>protobuf-java</artifactId>
            <version>3.9.1</version>
        </dependency>
        <dependency>
            <groupId>com.google.protobuf</groupId>
            <artifactId>protobuf-java-util</artifactId>
            <version>3.9.1</version>
        </dependency>
    </dependencies>
```


## 关键语法

### proto文件夹

上述插件默认编译src/main/proto目录下的.proto文件，其他目录下的.proto文件无法编译。因此需要创建src/main/proto目录，并把proto文件全部放到该目录下。

### 名称冲突

.proto文件中java_outer_classname项指定类生成的Java类名。如果没有设置java_outer_classname，那么生成的Java类名默认就是.proto文件名。

即，java_outer_classname或.proto文件为Java外部类名，message名为Java内部类名；二者不能一直，因为在Java中，外部类和内部类不能同名。否则报以下错误：

    protoc did not exit cleanly. Review output for more information.

java_outer_classname设置如下：

    option java_outer_classname = "Student";

### 包名

设置java_package指定生成的Java类的包名。
例如：

    option java_package = "server.proto";

### proto版本
一般使用proto3比较好，特性更多：

    syntax = "proto3";

## proto文件编译解读

源proto文件如下，在内部类Student中定义类基本类型成员、枚举类型和内部类PhoneNumber：

```
syntax = "proto3";
//生成文件所在包名
option java_package = "server.proto";
//生成的java文件名
option java_outer_classname = "StudentProto";

message Student {
  int32 id = 1;
  string name = 2;
  string email = 3;
  //枚举类
  enum Sex {
    MAN = 0;
    WOMAN = 1;
  }
  Sex sex = 4 ;

  enum PhoneType{
    MOBILE = 0;
    HOME = 1;
    WORK = 2;
  }
  //内部类
  message PhoneNumber {
    string number = 1;
    PhoneType type = 2 ;
  }
  //集合
  repeated PhoneNumber phone = 5;
}

```

编译后，生成类StudentProto这个Java类，该类中，有Student内部类和StudentOrBuilder接口：

```
    public interface StudentOrBuilder extends MessageOrBuilder {
        int getId();

        String getName();

        ByteString getNameBytes();

        String getEmail();

        ByteString getEmailBytes();

        int getSexValue();

        StudentProto.Student.Sex getSex();

        List<StudentProto.Student.PhoneNumber> getPhoneList();

        StudentProto.Student.PhoneNumber getPhone(int var1);
    }
```

Student类实现了StudentOrBuilder接口：

```

public static final class Student extends GeneratedMessageV3 implements StudentProto.StudentOrBuilder {
        public static final int ID_FIELD_NUMBER = 1;
        private int id_;
        public static final int NAME_FIELD_NUMBER = 2;
        private volatile Object name_;
        public static final int EMAIL_FIELD_NUMBER = 3;
        private volatile Object email_;
        public static final int SEX_FIELD_NUMBER = 4;
        private int sex_;
        public static final int PHONE_FIELD_NUMBER = 5;
        private List<StudentProto.Student.PhoneNumber> phone_;
        
        //内部类Student实现StudentOrBuilder的get方法,省略...
        //并实现类set方法，省略...
        getId()...
        setId()...
        getName()...
        setName()...
        getEmail()...
        setEmail()...
        getSex()...
        setSex()...
        getPhone()...
        setPhone()...
```

同理，在Student内部，也有PhoneNumberOrBuilder和PhoneNumber类：

```
public interface PhoneNumberOrBuilder extends MessageOrBuilder {
            String getNumber();

            ByteString getNumberBytes();

            int getTypeValue();

            StudentProto.Student.PhoneType getType();
        }
```
PhoneNumber类实现PhoneNumberOrBuilder的get方法：

```
 public static final class PhoneNumber extends GeneratedMessageV3 implements StudentProto.Student.PhoneNumberOrBuilder {
            public static final int NUMBER_FIELD_NUMBER = 1;
            private volatile Object number_;
            public static final int TYPE_FIELD_NUMBER = 2;
            private int type_;
            
            //get和set方法，省略...
            get...
            set...
 }
```



