package tech.vietinfo.vietinfomodule;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.MethodChannel;

public class VietinfoActivity extends AppCompatActivity {

    private static final String CHANNEL = "tech.vietinfo.demo/battery";
    private static final String TAG_FLUTTER_FRAGMENT = "flutter_fragment";
    private static final String MY_ID = "1";
    private static final int Fragment_ID = 10101010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);

        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(Fragment_ID);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.addView(frameLayout);
        setContentView(linearLayout);

        String contentInput = getIntent().getStringExtra("noidung");

        Log.d("MTL", "onCreate: " + contentInput);
        // Get a reference to the Activity's FragmentManager to add a new
        // FlutterFragment, or find an existing one.
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Attempt to find an existing FlutterFragment,
        // in case this is not the first time that onCreate() was run.
        FlutterFragment flutterFragment = (FlutterFragment) fragmentManager
                .findFragmentByTag(TAG_FLUTTER_FRAGMENT);

        // Create and attach a FlutterFragment if one does not exist.
        if (flutterFragment == null) {
            flutterFragment = FlutterFragment.withCachedEngine(MY_ID).build();

            fragmentManager
                    .beginTransaction()
                    .add(
                            Fragment_ID,
                            flutterFragment,
                            TAG_FLUTTER_FRAGMENT
                    )
                    .commit();

            FlutterEngine flutterEngine = new FlutterEngine(this);
            flutterEngine
                    .getDartExecutor()
                    .executeDartEntrypoint(
                            DartExecutor.DartEntrypoint.createDefault()
                    );

            FlutterEngineCache
                    .getInstance()
                    .put(MY_ID, flutterEngine);

            new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                    .setMethodCallHandler(
                            (call, result) -> {
                                if (call.method.equals("helloFromNativeCode")) {
                                    //String text = call.argument("text");
//                                    String content = getContent(contentInput);
//
//                                    if (!content.equals("")) {
//                                        result.success(content);
//                                    } else {
//                                        result.error("UNAVAILABLE", "Content is null.", null);
//                                    }
                                } else {
                                    result.notImplemented();
                                }
                            }
                    );
        }
    }
}
