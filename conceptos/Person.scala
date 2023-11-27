class Person (var surName: String, var lastName: String, var age:Integer)



/* equivalente al java: 
public class Person {

    private String surName;
    private String lastName;
    private int age;

    public Person(String surName,String lastName,int age){
        setSurName(surName);
        setLastName(lastName);
        setAge(age);
    }

    public String getSurName(){
        return this.surName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public int getAge(){
        return this.age;
    }
    public void setSurName(String value){
        this.surName = value;
    }
    public void setLastName(String value){
        this.lastName = value;
    }
    public void setAge(int value){
        this.age = value;
    }

}
*/