package info.lamatricexiste.network;


import info.lamatricexiste.network.Network.HardwareAddress;
import info.lamatricexiste.network.Network.HostBean;
import info.lamatricexiste.network.Network.NetInfo;
import info.lamatricexiste.network.Utils.Export;
import info.lamatricexiste.network.Utils.Help;
import info.lamatricexiste.network.Utils.Prefs;
import info.lamatricexiste.network.Utils.Save;
import info.lamatricexiste.network.*;

import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;



import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.DialogInterface.OnClickListener;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import info.lamatricexiste.network.Utils.Prefs;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;



public class AdditionService extends Service {
	
	   public interface AdditionServiceClient {
	        void exampleServiceClientMethod();
	    }

	    private WeakReference<AdditionServiceClient> mClient;

	    public void setServiceClient(AdditionServiceClient client) {
	        if(client == null) {
	            mClient = null;
	            return;
	        }

	        mClient = new WeakReference<AdditionServiceClient>(client);
	    }

	    public class ExampleBinder extends Binder {
	    
	    	AdditionService getService() {
	            return AdditionService.this;
	        }
	    }

	    private IBinder mBinder = new ExampleBinder();

	    @Override
	    public IBinder onBind(Intent intent) {
	        return mBinder;
	    }
}
