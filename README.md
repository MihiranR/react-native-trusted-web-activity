
# react-native-trusted-web-activity

This module can be used to implement Android's Trusted Web Activity in a React Native project. 
This is only available for Android.

## Getting started

`$ npm install react-native-trusted-web-activity --save`

### Mostly automatic installation

`$ react-native link react-native-trusted-web-activity`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNTrustedWebActivityPackage;` to the imports at the top of the file
  - Add `new RNTrustedWebActivityPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-trusted-web-activity'
  	project(':react-native-trusted-web-activity').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-trusted-web-activity/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-trusted-web-activity')
  	```

## Setup
1. In your `android/app/src/main/AndroidManifest.xml` file add the following inside the `<application>` tag,
	
	```
	<activity android:name="android.support.customtabs.trusted.LauncherActivity">
        <meta-data
            android:name="android.support.customtabs.trusted.DEFAULT_URL"
            android:value="${defaultUrl}"/>

        <intent-filter>
            <action android:name="android.intent.action.VIEW"/>

            <category android:name="android.intent.category.DEFAULT"/>
            <category android:name="android.intent.category.BROWSABLE"/>

            <data
                android:host="${hostName}"
                android:scheme="https"/>
        </intent-filter>
    </activity>
	```
2. In your `android/app/build.gradle`,

	```
	defaultConfig {
		...
		manifestPlaceholders = [
                hostName       : "www.example.com",		//replace with the url of your PWA
                defaultUrl     : "https://www.example.com",		//replace with the url of your PWA
                assetStatements: '[{ "relation": ["delegate_permission/common.handle_all_urls"], ' +
                        '"target": {"namespace": "web", "site": "https://www.example.com"}}]'
        ] 	//replace with the url of your PWA

        buildConfigField "String", "DEFAULT_URL", "\"${manifestPlaceholders.defaultUrl}\""
        multiDexEnabled true
		...
	}
	```

	and,

	```
	dependencies{
		...
		implementation "com.github.GoogleChrome.custom-tabs-client:customtabs:3a71a75c9f"
		...
	}
	```

## Usage
```javascript
import RNTrustedWebActivity from 'react-native-trusted-web-activity';

//Replace the url with your PWA's URL
RNTrustedWebActivity.goToPWA('https://www.example.com');

```
  