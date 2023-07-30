package cl.bci.evaluacion;

import cl.bci.evaluacion.entity.AdminEntity;
import cl.bci.evaluacion.entity.RoleEntity;
import cl.bci.evaluacion.service.IAdminService;
import cl.bci.evaluacion.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@Slf4j
public class EvaluacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluacionApplication.class, args);
	}

	/**
	 * Script for creation of dummy admins, in order to test the JWT integration
	 *
	 */
	@Bean
	CommandLineRunner run(IAdminService adminService, IRoleService roleService){
		return args -> {
			String roleAdmin = "ROLE_ADMIN";
			String roleSuperAdmin = "ROLE_SUPERADMIN";

			roleService.saveRole(RoleEntity.builder().rolename(roleAdmin).build());
			roleService.saveRole(RoleEntity.builder().rolename(roleSuperAdmin).build());

			adminService.saveAdmin(AdminEntity.builder().username("bwayne").password("batman").build());
			adminService.saveAdmin(AdminEntity.builder().username("ckent").password("superman").build());

			adminService.addRoleToAdmin("bwayne", roleAdmin);
			adminService.addRoleToAdmin("ckent", roleSuperAdmin);
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
