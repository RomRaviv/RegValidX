# RegValidX
A library that provides an easy check for a registration page. 
Works only with EditText Views, scans the views of the activity and checks the EditTexts.

# Setup
Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.RomRaviv:RegValidX:Tag'
	}
  
  # Usage
  
  Make sure you use this in the registration activity.
  
  `ViewsValidator validator = new ViewsValidator(this);`
  
 To use the validator:
 
 ` try{
                validator.validate();
                Toast. makeText(getApplicationContext(),"All fields are valid!",Toast. LENGTH_SHORT).show();
            }catch (IllegalArgumentException ex){
                Toast. makeText(getApplicationContext(),ex.getMessage(),Toast. LENGTH_SHORT).show();
    }`
