import java.util.ArrayList;
import java.util.Iterator;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class Bank {
    
    private String acctNo;
    
    private ArrayList<User> userAccounts = new ArrayList<>();
    
    private ArrayList<Account> accounts = new ArrayList<>();
    
    private boolean hasSerializedOnce;
    
    public Bank(){}
    
    public void setAcctNo(String acctNo){
        this.acctNo = acctNo;
    }
    
    public String getAcctNo(){
        return acctNo;
    }
    
    public void setUserAccounts(ArrayList<User> userAccounts){
        this.userAccounts = userAccounts;
    }
    
    //insert a new user account into arraylist or sent for individual serialization
    public void addUserAccount(User user){
        if(hasSerializedOnce){
            writeUserToFile(user);
        }else{
            userAccounts.add(user);
        }
    }
    
    //retrieve all user accounts 
    public ArrayList<User> getUserAccounts(){
        return userAccounts;
    }
    
    //retreive user account by account number
    public User getUserByAccountNo(String acctNo){
        for(User u : userAccounts){
            if(u.getAcctNo().equals(acctNo)){
                return u;
            }
        }
        return null;
    }
    
    //update user info by account number
    public void updateUserInfo(User userAccounts, String acctNo){
        Iterator iterate = this.userAccounts.iterator();
        while(iterate.hasNext()){
            User u = (User)iterate.next();
            if(u.getAcctNo().equals(acctNo)){
                this.userAccounts.set(this.userAccounts.indexOf(u), userAccounts);
            }
        }
    }///////////if in arrylst can just us setter ??? why this
    
    //delete user account
    public void deleteUserInfo(User userAccounts){
        Iterator iterate = this.userAccounts.iterator();
        while(iterate.hasNext()){
            User u = (User)iterate.next();
            if(u.getAcctNo().equals(userAccounts.getAcctNo())){
                iterate.remove();
            }
        }
    }
    
    //Serializing user to file
    public void writeUserToFile(User user){
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("users.txt", true)))){
            oos.writeObject(user);
            oos.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //Deserializing users from file
    public User readUserFromFile(){
        User user = null;
        
        userAccounts.clear();
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("users.txt")))){
            user = (User)ois.readObject();
            userAccounts.add(user);
            int i = 0;
            while(!(user == null)){
                user = (User)ois.readObject();
                userAccounts.add(user);
                System.out.println(++i);
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("There is no more data to read from the file.");
        }finally{
        }
        return user;
    }
    
     //Serializing Accounts to file
    public void writeAccountToFile(Account account){
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("account.txt")))){
            oos.writeObject(account);
            oos.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //Deserializing Account from file
    public void readAccountFromFile(){
        try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("account.txt")))){
            while(true){
                ois.readObject();
            }
        }catch(IOException | ClassNotFoundException e){
            System.out.println("There is no more data to read from the file.");
        }finally{
        }
    }
    
    //save users in the ArrayList
    public void save() throws IOException, ClassNotFoundException {
        for(User u : userAccounts){
            writeUserToFile(u);
        }
        userAccounts.clear();
    }

}