import server.proto.StudentProto;

public class Test {
    public static void main(String[] args) {
        StudentProto.Student build = StudentProto.Student.newBuilder().setEmail("haha").setId(1).setName("yu").setSex(StudentProto.Student.Sex.WOMAN).setPhone(0, StudentProto.Student.PhoneNumber.newBuilder().setTypeValue(1).setTypeValue(StudentProto.Student.PhoneType.HOME_VALUE)).build();
        System.out.println(build.getSex());
    }
}
