#DotMatrixView

This is an Android library project providing a custom view that can display things on a grid of dots. When the displayed value changes, the dots that are changing fade in/out to mimic persistence of vision and the speed of reaction of 1980s style LED spots.

##Usage

Link the Android library project into your app project, then add it to your layout XML.

Be sure to add this namespace to the parent layout element, e.g.

``` xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:layout_width="fill_parent"
android:layout_height="fill_parent" >
```

This example uses most of the current features of DotMatrixView:

``` xml
<com.escapeindustries.dotmatrix.MatrixDisplay
android:id="@+id/surfacegrid"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_centerInParent="true"
app:backgroundColor="@android:color/black"
app:dotColor_changeListDim="@array/dim_color_change_list"
app:dotColor_changeListLit="@array/lit_color_change_list"
app:dotColor_changeListTiming="@array/color_change_timings"
app:dotColor_changeUpdater="countdown_color_updates"
app:dotColor_staticDim="@color/pink"
app:dotColor_staticLit="@color/dullPink"
app:dotPaddingBottom="1"
app:dotPaddingLeft="1"
app:dotPaddingRight="1"
app:dotPaddingTop="1"
app:dotRadius="4"
app:dotSpacing="2"
app:format="0 0 : 0 0 : 0 0"
app:transitionDuration="300"
app:valueUpdater="clock_per_second" />
```

##Developed by
Mark Roberts - mark@escapeindustries.com

##License
Copyright 2012 Mark Roberts

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.