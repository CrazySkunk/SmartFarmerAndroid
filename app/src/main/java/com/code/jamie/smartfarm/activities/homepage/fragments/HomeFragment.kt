package com.code.jamie.smartfarm.activities.homepage.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.adapters.HomeAdapter
import com.code.jamie.smartfarm.databinding.FragmentHomeBinding
import com.code.jamie.smartfarm.models.ShopItem

private const val PARAM1 = "param1"
private const val PARAM2 = "param2"

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val shopItems = ArrayList<ShopItem>()
        shopItems.add(
            ShopItem(
                "AgroVetenaian",
                "Haile selassie ave",
                "We sell all agrovet product tha you are looking for",
                R.drawable.chicken.toString()
            )
        )
        shopItems.add(
            ShopItem(
                "AgroVetenaian",
                "Haile selassie ave",
                "We sell all agrovet product tha you are looking for",
                R.drawable.chicken_amanda.toString()
            )
        )
        shopItems.add(
            ShopItem(
                "AgroVetenaian",
                "Haile selassie ave",
                "We sell all agrovet product tha you are looking for",
                R.drawable.chicken.toString()
            )
        )
        shopItems.add(
            ShopItem(
                "AgroVetenaian",
                "Haile selassie ave",
                "We sell all agrovet product tha you are looking for",
                R.drawable.chicken_amanda.toString()
            )
        )
        shopItems.add(
            ShopItem(
                "AgroVetenaian",
                "Haile selassie ave",
                "We sell all agrovet product tha you are looking for",
                R.drawable.chicken.toString()
            )
        )
        shopItems.add(
            ShopItem(
                "AgroVetenaian",
                "Haile selassie ave",
                "We sell all agrovet product tha you are looking for",
                R.drawable.chicken_amanda.toString()
            )
        )
        val homeAdapter = HomeAdapter(shopItems, null)
        binding.shopsRecycler.apply {
            clipToPadding = false
            adapter = homeAdapter
        }
        homeAdapter.setOnItemClickListener(object : HomeAdapter.OnItemClick {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    requireContext(),
                    "Item at position ${shopItems[position]} was clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return binding.root
    }

    fun newInstance(param1: String, param2: String): HomeFragment {
        val args = Bundle()
        val fragment = HomeFragment()
        fragment.arguments = args
        args.getString(PARAM1, param1)
        args.getString(PARAM2, param2)
        return fragment
    }

}