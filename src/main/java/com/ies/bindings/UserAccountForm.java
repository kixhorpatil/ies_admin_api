package com.ies.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * This class is used to bind data from the "Create Account" form in the frontend.
 * Fields from the form are stored in this class, for those field in which data
 * will be stored generate getters and setters using Lombok annotations.
 * 
 * Note: We use @Getter and @Setter instead of @Data to avoid generating a
 * toString() method. Generating toString() in entity classes with relationships
 * can cause stack overflow issues due to circular references.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountForm {

    // Define the fields to match the "Create Account" form

}