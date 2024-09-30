 @PostMapping("/login")
    public String login(Model model, @RequestParam("loginusername") String name, @RequestParam("loginpassword")String password){
        Connection connection=null;
        this.username=name;
        try{
            connection =DriverManager.getConnection(jdbcurl,"root","Logesh@121!");
            String sql="SELECT userpassword from signup WHERE username=?";
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1,name);
            ResultSet rs=statement.executeQuery();

            while(rs.next())
            {
                if(password.equals(rs.getString("userpassword"))){
                    return "mainpage";
                }
                else{
                    return "alert";
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }



        return "login";
    }
    @PostMapping("/signup")
    public  String signup(@RequestParam("signupusername")String name, @RequestParam("signuppassword") String password)
    {
        System.out.println("Inside signup");

        Connection connection = null;

        try{

            connection = DriverManager.getConnection(jdbcurl,"root","Logesh@121!");
            String qq="select count(*) from signup where username=?";
            System.out.println("renned1");
            PreparedStatement statement0= connection.prepareStatement(qq);
            System.out.println("renned2");
            statement0.setString(1,name);
            System.out.println("renned3");
            ResultSet rs1= statement0.executeQuery();
          if(rs1.getInt(1)>0){
                if(rs1.next() && rs1.getInt(1)>0){
                    System.out.println("user already present");
                    return "alert3";
                }

               return "alert3";
      }
            String sql= "insert into signup values(?,?)";
            PreparedStatement statement= connection.prepareStatement(sql);
            //when we use varialbles we use prepare statement or we use statement/
            statement.setString(1,name);/* to replace the quetion mark we use preaparestatement and set the value to ?*/
            statement.setString(2,password);
            System.out.println("added");
            statement.executeUpdate();


        }
        catch (Exception e){
            System.out.println(e);
        }
        return "login";
    }

