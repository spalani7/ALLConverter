package com.example.allconverter;

import java.math.BigDecimal;
import java.util.Locale;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {
	public float var1, var2;
	public String varStr1,varStr2;
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		setContentView(R.layout.fragment_main_dummy);
		
		spinnerInit(R.array.Temp_array);
		
		ET1();
		ET2();
		
		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
						setContentView(R.layout.fragment_main_dummy);
					
						switch (position) {
						case 0:
							spinnerInit(R.array.Temp_array);
							ET1();
							ET2();
							break;
						case 1:
							//ET1(0,100,0);
							//ET2(0,0.01f,0);
							break;
						case 2:
							//ET1(0,(1/3.6f),0);
							//ET2(0,3.6f,0);
							break;
						default:
							break;
						}
						};
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 6;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
		//	TextView dummyTextView = (TextView) rootView
			//		.findViewById(R.id.textView1);
			//dummyTextView.setText(Integer.toString(getArguments().getInt(
				//	ARG_SECTION_NUMBER)));
			return rootView;
		}
	}
	

	public static double round(double unrounded, int precision)
	{
	    BigDecimal bd = new BigDecimal(unrounded);
	    bd = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
	    return bd.doubleValue();
	}
	public void spinnerInit(int arr)
	{
				
		Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
		Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
		        arr,R.layout.spinner_layout);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(R.layout.spinner_layout);
		// Apply the adapter to the spinner
		spinner1.setAdapter(adapter);
		spinner2.setAdapter(adapter);

		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener1());
		spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener2());

		
	}
	public void ET1()
	{
		EditText eT1 = (EditText) findViewById(R.id.eT1);
		eT1.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s) 
			{	
		EditText eT1 = (EditText) findViewById(R.id.eT1);  
		EditText eT2 = (EditText) findViewById(R.id.eT2);
		Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
		Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
		int spin1pos,spin2pos;
		spin1pos=spinner1.getSelectedItemPosition();
		spin2pos=spinner2.getSelectedItemPosition();
		final double result;
		if (eT1.isFocused()){
				try
				{
				var1 = Float.parseFloat(eT1.getText().toString());
				}
				catch (NumberFormatException e)
				{
					var1=0;
				}
				result=result1(spin1pos,spin2pos);
				varStr2 = String.valueOf(result);
				eT2.setText(varStr2);
			}
			
			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		});
	}
	public void ET2()
		{
		EditText eT2 = (EditText) findViewById(R.id.eT2);
		eT2.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s) 
			{	
				EditText eT1 = (EditText) findViewById(R.id.eT1);  
				EditText eT2 = (EditText) findViewById(R.id.eT2);
				Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
				Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
				int spin1pos,spin2pos;
				spin1pos=spinner1.getSelectedItemPosition();
				spin2pos=spinner2.getSelectedItemPosition();
			final double result;
			if (eT2.isFocused())
				{
					try
					{	
						var2 = Float.parseFloat(eT2.getText().toString());
					}
					catch (NumberFormatException e)
					{
						var2=0;
					}
					result=result2(spin1pos,spin2pos);
					varStr1 = String.valueOf(result);
					eT1.setText(varStr1);
					
				}
			
			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after){}
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		});
	   }

	public void ET1_init()
	{
			
		EditText eT1 = (EditText) findViewById(R.id.eT1);  
		EditText eT2 = (EditText) findViewById(R.id.eT2);
		Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
		Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
		int spin1pos,spin2pos;
		spin1pos=spinner1.getSelectedItemPosition();
		spin2pos=spinner2.getSelectedItemPosition();
		final double result;
				try
				{
				var1 = Float.parseFloat(eT1.getText().toString());
				}
				catch (NumberFormatException e)
				{
					var1=0;
					
				}
				result=result1(spin1pos,spin2pos);
				varStr2 = String.valueOf(result);
				eT2.setText(varStr2);
	}
	public void ET2_init()
	{
			EditText eT1 = (EditText) findViewById(R.id.eT1);  
			EditText eT2 = (EditText) findViewById(R.id.eT2);
			Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
			Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
			int spin1pos,spin2pos;
			spin1pos=spinner1.getSelectedItemPosition();
			spin2pos=spinner2.getSelectedItemPosition();
		final double result;
				try
				{	
					var2 = Float.parseFloat(eT2.getText().toString());
				}
				catch (NumberFormatException e)
				{
					var2=0;
					
				}
				result=result2(spin1pos,spin2pos);
				varStr1 = String.valueOf(result);
				eT1.setText(varStr1);
   }
	
	
	public class CustomOnItemSelectedListener1 implements OnItemSelectedListener
{
		 
		  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) 
		  {
			EditText eT2 = (EditText) findViewById(R.id.eT2); 
			//Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
			//Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
			//int spin1pos,spin2pos;
			//spin1pos=spinner1.getSelectedItemPosition();
			//spin2pos=spinner2.getSelectedItemPosition();
			/*Toast.makeText(parent.getContext(), 
					"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString()+spin1pos+spin2pos,
					Toast.LENGTH_SHORT).show();
			
			
			Toast.makeText(parent.getContext(),"Spinner1 is Enabled ",Toast.LENGTH_SHORT).show();*/
			
			ET2_init();
			//eT1.setFocusableInTouchMode(true);
			eT2.requestFocus();
			/*switch (0) 
			{
			
			case 0:
				tempcalc1_init(spin1pos,spin2pos);
				tempcalc2_init(spin1pos,spin2pos);
				break;
			default:
				break;
			}*/
		  }
		 
		  @Override
		  public void onNothingSelected(AdapterView<?> arg0) 
		  {
			// TODO Auto-generated method stub
		  }
		 
	}

	
	public class CustomOnItemSelectedListener2 implements OnItemSelectedListener
	{
			 
			  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) 
			  {
				EditText eT1 = (EditText) findViewById(R.id.eT1); 
				//Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
				//Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
				//int spin1pos,spin2pos;
				//spin1pos=spinner1.getSelectedItemPosition();
				//spin2pos=spinner2.getSelectedItemPosition();
				/*Toast.makeText(parent.getContext(), 
						"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString()+spin1pos+spin2pos,
						Toast.LENGTH_SHORT).show();
				
				Toast.makeText(parent.getContext(),"Spinner2 is Enabled ",Toast.LENGTH_SHORT).show();*/
				
				ET1_init();
				eT1.requestFocus();
				//eT2.setFocusableInTouchMode(true);
				/*switch (0) 
				{
				
				case 0:
					tempcalc1_init(spin1pos,spin2pos);
					tempcalc2_init(spin1pos,spin2pos);
					break;
				default:
					break;
				}*/
			  }
			  @Override
			  public void onNothingSelected(AdapterView<?> arg0) 
			  {
				// TODO Auto-generated method stub
			  }
			 
		}
	
	public double result1 (int spin1pos,int spin2pos)
	{
		double res=0;
		switch (spin1pos) {
		case 0:							// CELSIUS
			switch (spin2pos) {
			case 0:								// CELSIUS
				res=round(var1,4);
				break;
			case 1:								// FARENHEIT
				res=round((var1*1.8f)+32,4);
				break;
			case 2:								// KELVIN
				res=round((var1+273.15f),4);
				break;
			default:
				break;
			}
			break;
		case 1:							// FARENHEIT
			switch (spin2pos) {
			case 0:								// CELSIUS
				res=round(((var1-32)/1.8f),4);
				break;
			case 1:								// FARENHEIT
				res=round(var1,4);
				break;
			case 2:								// KELVIN
				res=round(((var1-32)/1.8f)+273.15f,4);
				break;
			default:
				break;
			}
			break;
		case 2:							// KELVIN
			switch (spin2pos) {
			case 0:								// CELSIUS
				res=round((var1-273.15f),4);
				break;
			case 1:								// FARENHEIT
				res=round(((var1-273.15f)*1.8f)+32,4);
				break;
			case 2:								// KELVIN
				res=round(var1,4);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		return res;
	}
	
	public double result2 (int spin1pos,int spin2pos)
	  {
		double res=0;
		switch (spin1pos) {
		case 0:							// CELSIUS
			switch (spin2pos) {
			case 0:								// CELSIUS
				res=round(var2,4);
				break;
			case 1:								// FARENHEIT
				res=round((var2-32)*0.5556f,4);
				break;
			case 2:								// KELVIN
				res=round((var2-273.15f),4);
				break;
			default:
				break;
			}
			break;
		case 1:							// FARENHEIT
			switch (spin2pos) {
			case 0:								// CELSIUS
				res=round((var2*1.8f)+32,4);
				break;
			case 1:								// FARENHEIT
				res=round(var2,4);
				break;
			case 2:								// KELVIN
				res=round(((var2-273.5f)*1.8f)+32,4);
				break;
			default:
				break;
			}
			break;
		case 2:							// KELVIN
			switch (spin2pos) {
			case 0:								// CELSIUS
				res=round((var2+273.15f),4);
				break;
			case 1:	
				res=round(((var2-32)/1.8f)+273.15f,4);
				break;// FARENHEIT
			case 2:								// KELVIN
				res=round(var2,4);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		return res;
	  }
	
}
