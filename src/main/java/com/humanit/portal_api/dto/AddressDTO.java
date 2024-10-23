package com.humanit.portal_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class AddressDTO {
        @Schema(description = "Address ID", example = "1")
        private Long id;

        @Schema(description = "Street name", example = "Avenue example")
        private String street;

        @Schema(description = "Number", example = "110")
        private String number;

        @Schema(description = "City name", example = "São Paulo")
        private String city;

        @Schema(description = "State or region", example = "SP")
        private String state;

        @Schema(description = "PostalCode", example = "122000-000")
        private String postalCode;

        @Schema(description = "Country name", example = "Brazil")
        private String country;

        @Schema(description = "User Account Id", example = "1")
        private Long userAccountId;

        public AddressDTO(Long id, String street, String number, String city, String state, String postalCode, String country, Long userAccountId) {
                this.id = id;
                this.street = street;
                this.number = number;
                this.city = city;
                this.state = state;
                this.postalCode = postalCode;
                this.country = country;
                this.userAccountId = userAccountId;
        }

        public Long getId() {
                return id;
        }

        public AddressDTO setId(Long id) {
                this.id = id;
                return this;
        }

        public String getStreet() {
                return street;
        }

        public AddressDTO setStreet(String street) {
                this.street = street;
                return this;
        }

        public String getNumber() {
                return number;
        }

        public AddressDTO setNumber(String number) {
                this.number = number;
                return this;
        }

        public String getCity() {
                return city;
        }

        public AddressDTO setCity(String city) {
                this.city = city;
                return this;
        }

        public String getState() {
                return state;
        }

        public AddressDTO setState(String state) {
                this.state = state;
                return this;
        }

        public String getPostalCode() {
                return postalCode;
        }

        public AddressDTO setPostalCode(String postalCode) {
                this.postalCode = postalCode;
                return this;
        }

        public String getCountry() {
                return country;
        }

        public AddressDTO setCountry(String country) {
                this.country = country;
                return this;
        }

        public Long getUserAccountId() {
                return userAccountId;
        }

        public AddressDTO setUserAccountId(Long userAccountId) {
                this.userAccountId = userAccountId;
                return this;
        }
}
