package tdea.construccion2.app;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tdea.construccion2.app.controller.LoginController;


@SpringBootApplication
public class AppApplication implements CommandLineRunner {
	private static Scanner reader = new Scanner(System.in);
	@Autowired
	private LoginController loginController;
	
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean run = true;
		while (run)
			try {
				run = runApplication();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		System.out.print("Deteniendo la aplicacion");
		System.exit(0);
	}
	
	public boolean runApplication() throws Exception {
		String menu = "-----------INGRESO DE APP VETERINARIA ----------\n1.Para iniciar sesion \n2.Para cerrar la aplicacion";
		System.out.println(menu);
		String option = reader.nextLine();
		switch (option) {
			case "1":
				loginController.login();
				return true;
			case "2":
				return false;
			default: {
				System.out.print("Ingrese una opcion valida");
				return true;
			}	
		}
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

}
