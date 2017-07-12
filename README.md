# Lawnchair - [![Travis](https://img.shields.io/travis/Deletescape-Media/Lawnchair.svg)](https://travis-ci.org/Deletescape-Media/Lawnchair) - [![Github All Releases](https://img.shields.io/github/downloads/Deletescape-Media/Lawnchair/total.svg)](https://github.com/Deletescape-Media/Lawnchair/releases) - [![Crowdin](https://d322cqt584bo4o.cloudfront.net/lawnchairandroid/localized.svg)](https://crowdin.com/project/lawnchairandroid) -[![Donate on PayPal](https://img.shields.io/badge/PayPal-Donate%20Now-brightgreen.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=E2EFKTUC62KDQ)


![What in launcher creation?](meme.jpg)

Yes, I know, there are already waaaay to many launchers out there.
Yes I know this won't be successful. But I am only doing this for fun (and for the pun :innocent:) anyways.
I don't even know what kind of features I want to add to this, so if you have a great idea just open an issue or submit a PR!

## Now what does this cost?

Nothing, I am promising that Lawnchair, including all it's features, will always be free! But if you really like it, you can still pay me a beer or something on [PayPal](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=E2EFKTUC62KDQ).

## How to Contribute?

Contributing to Lawnchair is pretty straight forward! Fork the project, clone your fork and start coding!

Before implementing any new big features I would be happy if you would either hit me up in an issue, or on [Telegram](https://t.me/deletescape). I probably won't reject it, but this is just to make sure that you don't invest your time for something I won't include.

Unfortunately I cannot share a decrypted version of `google-services.json`, to build without this file you will have to comment out the following lines in `build.gradle`
 * `classpath 'com.google.gms:google-services:3.1.0'` (at the beginning of the file)
 * `apply plugin: 'com.google.gms.google-services'` (at the bottom of the file)
 
> **DO NOT PUSH THIS CHANGE OR I WILL NOT ACCEPT YOUR PR UNTIL YOU CHANGE THIS!**
