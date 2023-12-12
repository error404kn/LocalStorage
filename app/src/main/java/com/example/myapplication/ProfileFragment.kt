package com.example.myapplication

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentProfileBinding
import kotlinx.coroutines.runBlocking
import org.intellij.lang.annotations.Language
import java.util.Locale
import kotlin.math.log

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by activityViewModels()

   private lateinit var myPref: MySharedPreferences
   private lateinit var myDataStore: MyDataStore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myPref = MySharedPreferences(requireContext())
        myDataStore = MyDataStore(requireContext())
        init()
    }

    private fun getResourceKeyMapping(language: String): Map<String, Int> {
        return when (language) {
            "Georgia" -> mapOf(
                "edit_profile" to R.string.edit_profile_ka,
                "change_language" to R.string.change_languange_ka,
                "logout" to R.string.logout_ka,
                "profile" to R.string.profile_ka
            )
            else -> mapOf(
                "edit_profile" to R.string.edit_profile_en,
                "change_language" to R.string.change_languange_en,
                "logout" to R.string.logout_en,
                "profile" to R.string.profile_en
            )
        }
    }



    private fun init(){
        binding.saveBtn.setOnClickListener{
            myPref.saveData("name", binding.editText.text.toString())

            runBlocking {
                myDataStore.saveData("name", binding.editText.text.toString())
            }

            binding.editText.text = null
        }
        binding.navigateBtn.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment2)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}