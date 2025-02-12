package User;

public class Model {

    String username;
    String userid;
    String userpw;
    String phonenumber;
    String birthday;
    String gender;

    public Model(String a, String b, String c, String d, String e,String f){
        this.username = a;
        this.userid = b;
        this.userpw = c;
        this.phonenumber = d;
        this.birthday = e;
        this.gender = f;
    }

    public Model(String a, String b, String c, String d){
        this.username = a;
        this.userid = b;
        this.userpw = c;
        this.phonenumber = d;
    }

    public Model(){}

    public String getUserName(){
        return username;
    }
    public void setUserName(String name){
        this.username = name;
    }

    public String getUserId(){
        return userid;
    }
    public void setUserId(String id){
        this.userid = id;
    }

    public String getUserPw(){
        return userpw;
    }
    public void setUserPw(String password){
        this.userpw = password;
    }

    public String getPhoneN(){
        return phonenumber;
    }
    public void setPhoneN(String number){
        this.phonenumber = number;
    }


    public void setGender(String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }
    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

    public String getBirthday(){
        return birthday;
    }


}
