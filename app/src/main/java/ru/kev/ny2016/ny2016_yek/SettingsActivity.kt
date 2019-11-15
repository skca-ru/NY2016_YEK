package ru.kev.ny2016.ny2016_yek

import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.text.Spanned
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat


class InputFilterMinMax(min:Int, max:Int):InputFilter {
    //TODO: сделать нормальный фильтр
    private var min:Int= 0.toInt()
    private var max:Int = 0.toInt()
    init{
        this.min = min
        this.max = max
    }
    override fun filter(source:CharSequence, start:Int, end:Int, dest:Spanned, dstart:Int, dend:Int): CharSequence?
    {
        print(end);
        if (end >4)
            return null
        else
            return "" ;}


}
class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            val editDuration = preferenceManager.findPreference<EditTextPreference>("aaDuration");
            editDuration?.setOnBindEditTextListener { editText -> editText.inputType =  InputType.TYPE_CLASS_NUMBER; editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(4));};
            //val et  =  editDuration?.getEditText(); //.getText();
            //et.filters = arrayOf<InputFilter>(InputFilterMinMax(0,9999));//arrayOf<InputFilter>(InputFilterMinMax(0, 9999)));


        }
    }
}