# kotlin-dsl-samples
This is a sample repo to show how Kotlin DSL help in adding values to our projects. DL simplifies code plus helps in generalising solutions. Currently this repo contains solutions for Android specific APIs only but anyone is free to add and suggest any DSL that can benefit the others. I have a dsl package in all my recent projects where i have added some DSLs that i now use regularly one specifically related to Dialogs.


### Alert Dialog
This example shows how to create default Alert Dialog DSL. It's mostly a buildup to next examples.

```
    alert {
          title = "Hey Title"
          description = "Hey Description"
          alertContext = context
          positiveButton = {
              Toast.makeText(context, "Yes", Toast.LENGTH_LONG).show()
          }

          negativeButton = {
              Toast.makeText(context, "And No", Toast.LENGTH_LONG).show()
          }
     }
```        

### Alert Dialog with CustomView
This example shows how to create custom Alert Dialogs. We can pass any **layoutId** here then in setCustomView we can set views data and implement callbacks. Though use of **findViewById** use here feels annoying since we have got kotlin android extensions now. Anyway don't worry we'll fix that.

```
    val alertData = AlertData(
                    "This is a Custom Dialog Title",
                    "This is a Custom Dialog Description"
            )
      customAlert {

          layoutId = R.layout.layout_dialog
          setCustomView = { it: View, dialog: AlertDialog ->

              it.findViewById<TextView>(R.id.title).text = alertData.title
              it.findViewById<TextView>(R.id.description).text = alertData.description

              it.findViewById<TextView>(R.id.accept).setOnClickListener {
                  Toast.makeText(context, "accept button click", Toast.LENGTH_LONG).show()
                  dialog.dismiss()
              }

              it.findViewById<TextView>(R.id.reject).setOnClickListener {
                  Toast.makeText(context, "reject button click", Toast.LENGTH_LONG).show()
                  dialog.dismiss()
              }

          }
      }
```    

### Dialog using Dialog Fragment
This example shows how to create dialog just pass any **layoutId** and then in setCustomView set data or implement callbacks BTW here you don't have to use **findViewById** anymore. Now making dialogs in Android not only becomes easy but beautiful.

```
      dialog {
          layoutId = R.layout.layout_dialog
          setCustomView = {
              it.title.text = getString(R.string.fragment_dialog_title)
              it.description.text = getString(R.string.fragment_dialog_description)

              it.accept.setOnClickListener {
                  Toast.makeText(context, "accept button click", Toast.LENGTH_LONG).show()
              }

              it.reject.setOnClickListener {
                  Toast.makeText(context, "reject button click", Toast.LENGTH_LONG).show()
              }

          }
      }
```    

### Bottom sheet Dialog using BottomSheetDialogFragment
Just like Dialog fragment we can create DSL for BottomSheetDialog fragment or any other similar use cases.
```
      bottomSheetDialog {
          layoutId = R.layout.layout_dialog
          setCustomView = {
              it.title.text = getString(R.string.fragment_dialog_title)
              it.description.text = getString(R.string.fragment_dialog_description)

              it.accept.setOnClickListener {
                  Toast.makeText(context, "accept button click", Toast.LENGTH_LONG).show()
              }

              it.reject.setOnClickListener {
                  Toast.makeText(context, "reject button click", Toast.LENGTH_LONG).show()
              }

          }
      }

```   


### Request DSL permission using DSL
This is a basic example to show how it can be so easy to ask permission in Android using below DSL. You can use this example as it is if you only require one permission oe more than one permission you can add your implementation in PermissionActivity class.
```
      val permissionsList = arrayOf(
              Manifest.permission.WRITE_EXTERNAL_STORAGE
      )

      getPermissions {
          permissions = permissionsList
          onPermissionGranted = {
              Toast.makeText(context, "Permission Given", Toast.LENGTH_LONG).show()
          }

          onPermissionDenied = {
              Toast.makeText(context, "Permission Denied", Toast.LENGTH_LONG).show()
          }
      }
  }
```    

### Checkout my other projects you may find them useful

 #### [CrashReporter](https://github.com/MindorksOpenSource/CrashReporter)
 
CrashReporter is a handy tool to capture app crashes and save them in a file.

 #### [Robin](https://github.com/balsikandar/Robin)
 
 Robin is a logging library for Bundle data passed between Activities and fragments. It also provides a callback to send screen views of user visited pages to your analytics client.
 
 #### [Android studio plugins](https://github.com/balsikandar/Android-Studio-Plugins)
 
 This is a list of all awesome and useful android studio plugins.



### TODO
Anything that improves code quality, improves it performance.
### Find this project useful? :heart:
* Support it by clicking the :star: button on the upper right of this page. :v:

### Contact - Let's connect and share knowledge
- [Twitter](https://twitter.com/balsikandar)
- [Github](https://github.com/balsikandar)
- [Medium](https://medium.com/@balsikandar.nsit)
- [Facebook](https://www.facebook.com/balsikandar)

### License

   ```
   Copyright (C) 2017 Bal Sikandar
   Copyright (C) 2011 Android Open Source Project

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   ```
