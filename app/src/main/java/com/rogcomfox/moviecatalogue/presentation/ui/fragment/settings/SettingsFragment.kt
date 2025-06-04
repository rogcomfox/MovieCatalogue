package com.rogcomfox.moviecatalogue.presentation.ui.fragment.settings

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rogcomfox.moviecatalogue.R
import com.rogcomfox.moviecatalogue.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bind data
        binding.tvDeveloper.text =
            Html.fromHtml(resources.getString(R.string.tv_developer), Html.FROM_HTML_MODE_LEGACY)
        val info = requireActivity().packageManager.getPackageInfo(requireActivity().packageName, 0)
        binding.tvAppName.text = resources.getString(
            R.string.tv_app_info,
            resources.getString(R.string.app_name),
            info.versionName
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}