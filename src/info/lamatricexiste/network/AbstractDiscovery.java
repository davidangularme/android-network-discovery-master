package info.lamatricexiste.network;

import info.lamatricexiste.network.Network.HostBean;
import info.lamatricexiste.network.Utils.Prefs;

import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.text.format.Formatter;
import android.util.Log;

public abstract class AbstractDiscovery extends AsyncTask<Void, HostBean, Void> {

    //private final String TAG = "AbstractDiscovery";

    protected int hosts_done = 0;
    final protected WeakReference<ActivityDiscovery> mDiscover;

    protected long ip;
    protected long start = 0;
    protected long end = 0;
    protected long size = 0;

    public AbstractDiscovery(ActivityDiscovery discover) {
    	Log.d("davidfff", "davidfff fffff AbstractDiscovery(ActivityDiscovery discover)");
        mDiscover = new WeakReference<ActivityDiscovery>(discover);
       
        
    }

    public void setNetwork(long ip, long start, long end) {
        this.ip = ip;
        this.start = start;
        this.end = end;
    }

    abstract protected Void doInBackground(Void... params);

    @Override
    protected void onPreExecute() {
        size = (int) (end - start + 1);
        if (mDiscover != null) {
            final ActivityDiscovery discover = mDiscover.get();
            if (discover != null) {
                discover.setProgress(0);
            }
        }
    }

    @Override
    protected void onProgressUpdate(HostBean... host) {
    	Log.d("davidfff", "davidfff fffff host " +host);
    	
    	try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    
                   // if (!inetAddress.isLoopbackAddress()) {
                    	Log.d("davidfff", "davidfff fffff " + inetAddress.getHostAddress().toString());
                    	Log.d("davidfff", "davidfff fffff name" +inetAddress.getHostName());
                    	
                   // }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
    	
        if (mDiscover != null) {
            final ActivityDiscovery discover = mDiscover.get();
            if (discover != null) {
                if (!isCancelled()) {
                    if (host[0] != null) {
                    	Log.d("davidfff", "davidfff deviceType = " + host[0].deviceType);
                    	Log.d("davidfff", "davidfff hardwareAddress = " + host[0].hardwareAddress);
                    	Log.d("davidfff", "davidfff nicVendor = " + host[0].nicVendor);
                    	Log.d("davidfff", "davidfff ipAddress = " + host[0].ipAddress);
                    	Log.d("davidfff", "davidfff os = " + host[0].os);
                    	Log.d("davidfff", "davidfff hostname = " + host[0].hostname);
                    	
                    	Log.d("davidfff", "davidfff string = " + host[0].toString());
                    	
                        discover.addHost(host[0]);
                    }
                    if (size > 0) {
                        discover.setProgress((int) (hosts_done * 10000 / size));
                    }
                }

            }
        }
        
       
    }

    @Override
    protected void onPostExecute(Void unused) {
        if (mDiscover != null) {
            final ActivityDiscovery discover = mDiscover.get();
            if (discover != null) {
                if (discover.prefs.getBoolean(Prefs.KEY_VIBRATE_FINISH,
                        Prefs.DEFAULT_VIBRATE_FINISH) == true) {
                    Vibrator v = (Vibrator) discover.getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(ActivityDiscovery.VIBRATE);
                }
                discover.makeToast(R.string.discover_finished);
                discover.stopDiscovering();
            }
        }
    }

    @Override
    protected void onCancelled() {
        if (mDiscover != null) {
            final ActivityDiscovery discover = mDiscover.get();
            if (discover != null) {
                discover.makeToast(R.string.discover_canceled);
                discover.stopDiscovering();
            }
        }
        super.onCancelled();
    }
}
