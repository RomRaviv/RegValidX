# RegValidX
A library that provides an easy check for a registration page. 
Works only with EditText Views, scans the views of the activity and checks the EditTexts.

# Setup
Step 1. Add the JitPack repository to your build file


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
Make sure each EditText in the layout has the attribute: "android:inputType = {TYPE}" where TYPE is the desired type.

For example:

         <EditText
            android:id="@+id/editTextEmail"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Email"
            android:inputType="textEmailAddress" />

In the registration activity:


  	ViewsValidator validator = new ViewsValidator(this); 
 	try{
		validator.validate();
		Toast. makeText(getApplicationContext(),"All fields are valid!",Toast. LENGTH_SHORT).show();
    }catch (IllegalArgumentException ex){
           Toast. makeText(getApplicationContext(),ex.getMessage(),Toast. LENGTH_SHORT).show();
	}

