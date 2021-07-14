package com.code.jamie.smartfarm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.models.PagerItem
import com.squareup.picasso.Picasso

class ItemsViewPagerAdapter(val context: Context, private val itemsList: List<PagerItem>) : PagerAdapter() {
    override fun getCount(): Int {
        return itemsList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        return super.instantiateItem(container, position)
        val layoutScreen = LayoutInflater.from(context).inflate(R.layout.pager_item,container,false)
        val image = layoutScreen.findViewById<ImageView>(R.id.account_pager_image)
        val text = layoutScreen.findViewById<TextView>(R.id.account_pager_textview)
        Picasso.get().load(itemsList[position].imageUrl!!).placeholder(R.drawable.chicken_amanda).into(image)
        text.text=itemsList[position].title
        container.addView(layoutScreen)
        return layoutScreen
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        super.destroyItem(container, position, `object`)
        container.removeView(`object`as View)
    }
}