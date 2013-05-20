package com.grappleunit.christmas;

import android.app.Application;
import org.acra.*;
import org.acra.annotation.*;

@ReportsCrashes(
	formKey = "",
	formUri = "http://grappleunit.com/buglover.php",
	mode = ReportingInteractionMode.SILENT
)
public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		ACRA.init(this);
	}

}
