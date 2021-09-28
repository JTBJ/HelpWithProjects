import java.io.*;

public class Main {
    
    private static User user;
    private static Account account;
    private static Bank bank;
    
    public Main(){
        this.user = new User();
        this.account = new Account();
        this.bank = new Bank();
    }
    
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		Main m = new Main();
		
		user.setFirstName("jessie");
		user.setLastName("Benton");
		user.setAcctNo("eweeere43409304");
		user.setBalance(80890423.90);
		user.setPassword("aj");
		
// 		bank.addUserAccount(user);
		
// 		System.out.println(bank.getUserAccounts());
		
// 		bank.updateUserInfo(user.setFirstName("Turner"), user.getAcctNo());
		
// 		System.out.println(bank.getUserAccounts());
		
// 		bank.deleteUserInfo(new User("Turner", "Benton", user.getAcctNo(), user.getBalance(), user.getPassword()));
		
// 		System.out.println(bank.getUserAccounts());

        // bank.writeUserToFile(user);
        
        Main m1 = new Main();
        user.setFirstName("jessie");
		user.setLastName("Benton");
		user.setAcctNo("eweeere43409304");
		user.setBalance(80890423.90);
		user.setPassword("aj");
        // bank.writeUserToFile(user);
		
        bank.readUserFromFile();
        System.out.println(bank.getUserAccounts());
        
        // try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("users.txt")))){
        //     User user = (User)ois.readObject();
        //     System.out.println(user);
        // }catch(IOException | ClassNotFoundException e){
        //     e.printStackTrace();
        // }
	}
}
