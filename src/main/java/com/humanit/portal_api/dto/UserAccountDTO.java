package com.humanit.portal_api.dto;

import com.humanit.portal_api.enumearator.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public class UserAccountDTO {
        private Long id;

        @Schema(description = "UserAccount's username", example = "joao_souza")
        private String username;

        @Schema(description = "UserAccount's password", example = "p@s$W0rd")
        private String password;

        @Schema(description = "UserAccount's first name", example = "Joao")
        private String firstName;

        @Schema(description = "UserAccount's last name", example = "Souza")
        private String lastName;

        @Schema(description = "UserAccount's email", example = "joao.souza@gmail.com")
        private String email;

        @Schema(description = "UserAccount's phone number", example = "+55 11 12345-6789")
        private String phoneNumber;

        @Schema(description = "UserAccount's birth date", example = "1990-01-01")
        private LocalDate birthDate;

        @Schema(description = "UserAccount's role", example = "USER")
        private Role role;

        @Schema(description = "UserAccount's addresses", example = " [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"street\": \"Avenue example\",\n" +
                "      \"number\": \"110\",\n" +
                "      \"city\": \"São Paulo\",\n" +
                "      \"state\": \"SP\",\n" +
                "      \"postalCode\": \"122000-000\",\n" +
                "      \"country\": \"Brazil\",\n" +
                "      \"userAccountId\": 1\n" +
                "    }\n" +
                "  ]")
        private List<AddressDTO> addressDTOS;

        public UserAccountDTO(Long id,
                              String username,
                              String password,
                              String firstName,
                              String lastName,
                              String email,
                              String phoneNumber,
                              LocalDate birthDate,
                              Role role,
                              List<AddressDTO> addressDTOS) {
                this.id = id;
                this.username = username;
                this.password = password;
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.phoneNumber = phoneNumber;
                this.birthDate = birthDate;
                this.role = role;
                this.addressDTOS = addressDTOS;
        }

        public Long getId() {
                return id;
        }

        public UserAccountDTO setId(Long id) {
                this.id = id;
                return this;
        }

        public String getUsername() {
                return username;
        }

        public UserAccountDTO setUsername(String username) {
                this.username = username;
                return this;
        }

        public String getPassword() {
                return password;
        }

        public UserAccountDTO setPassword(String password) {
                this.password = password;
                return this;
        }

        public String getFirstName() {
                return firstName;
        }

        public UserAccountDTO setFirstName(String firstName) {
                this.firstName = firstName;
                return this;
        }

        public String getLastName() {
                return lastName;
        }

        public UserAccountDTO setLastName(String lastName) {
                this.lastName = lastName;
                return this;
        }

        public String getEmail() {
                return email;
        }

        public UserAccountDTO setEmail(String email) {
                this.email = email;
                return this;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public UserAccountDTO setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
                return this;
        }

        public LocalDate getBirthDate() {
                return birthDate;
        }

        public UserAccountDTO setBirthDate(LocalDate birthDate) {
                this.birthDate = birthDate;
                return this;
        }

        public Role getRole() {
                return role;
        }

        public UserAccountDTO setRole(Role role) {
                this.role = role;
                return this;
        }

        public List<AddressDTO> getAddressDTOS() {
                return addressDTOS;
        }

        public UserAccountDTO setAddressDTOS(List<AddressDTO> addressDTOS) {
                this.addressDTOS = addressDTOS;
                return this;
        }
}
