package com.code.jamie.smartfarm.activities.homepage.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.code.jamie.smartfarm.adapters.FAQAdapter
import com.code.jamie.smartfarm.databinding.FragmentFaqsBinding
import com.code.jamie.smartfarm.models.FAQItem

private const val PARAM1 = "param1"
private const val PARAM2 = "param2"

class FAQsFragment : Fragment() {

    private lateinit var binding: FragmentFaqsBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(PARAM1)
            param2 = it.getString(PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFaqsBinding.inflate(inflater, container, false)
        val faqItems = ArrayList<FAQItem>()
        faqItems.add(FAQItem("Causes"))
        faqItems.add(FAQItem("Signs and Symptoms"))
        faqItems.add(FAQItem("Preventions and Measures"))
        faqItems.add(FAQItem("Control Measures"))
        val faqAdapter = FAQAdapter(faqItems, null)
        binding.faqsRecycler.apply {
            clipToPadding = false
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            hasFixedSize()
            adapter = faqAdapter
        }
        faqAdapter.setOnItemClickListener(object : FAQAdapter.OnItemClick {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    requireContext(),
                    "Item at position ${faqItems[position]} clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return binding.root
    }

    fun newInstance(param1: String, param2: String): FAQsFragment {
        val args = Bundle()

        val fragment = FAQsFragment()
        fragment.arguments = args
        args.getString(PARAM1, param1)
        args.getString(PARAM2, param2)
        return fragment
    }

}