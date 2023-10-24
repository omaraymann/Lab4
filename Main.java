public class Main {
    public static void main(String[] args) {
        EmployeeUserDatabase db = new EmployeeUserDatabase("employees.txt");
        System.out.println(db.getRecord("e5").lineRepresentation());
        //db.insertRecord(new EmployeeUser("8317","shiko","shiko@gmail.com","alex","012"));
        //db.SaveToFile();
        System.out.println(db.getRecord("8317").lineRepresentation());
    }
}


