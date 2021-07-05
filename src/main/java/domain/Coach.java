package domain;

public class Coach {
    private int id;
    private String name;
    private int sex;
    private String sexStr;
    private double salary;
    private int ability;
    private String abilityStr;
    private int type;
    private String typeStr;
    private int age;
    private String country;
    private Team team;




    public String getSexStr() {
        if (sex==0){
            sexStr="女";
        }else {
            sexStr="男";
        }
        return sexStr;
    }

    public String getAbilityStr() {
        if (ability==0){
            abilityStr="入门";
        }else if (ability==1){
            abilityStr="初等";
        }else if (ability==2){
            abilityStr="中等";
        }else if (ability==3){
            abilityStr="高级";
        }else if (ability==4){
            abilityStr="大师";
        }else if (ability==5){
            abilityStr="巨星";
        }
        return abilityStr;
    }

    public void setAbilityStr(String abilityStr) {
        this.abilityStr = abilityStr;
    }

    public String getTypeStr() {
        if (type==0){
            typeStr="进攻";
        }else if (type==1){
            typeStr="防守";
        }else if (type==2){
            typeStr="战术";
        }else if (type==3){
            typeStr="技能";
        }else if (type==4){
            typeStr="基础素质";
        }
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", salary=" + salary +
                ", ability=" + ability +
                ", type=" + type +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", team=" + team +
                '}';
    }
}
