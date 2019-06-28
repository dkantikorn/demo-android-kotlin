package com.example.hellokotlin

//import android.annotation.SuppressLint
//import android.bluetooth.BluetoothAdapter
//import android.content.Context
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import kotlinx.android.synthetic.main.activity_main.*
//
//typealias Arraylist<E> = java.util.ArrayList<E>
//
//class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
//
//    private lateinit var text_input: String;
//    private val list: ArrayList<String> = ArrayList();
//    private lateinit var adapter: CustomListView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//
//        adapter = CustomListView(this, list)
//        list_item.adapter = adapter
//        list_item.onItemClickListener = this
//        setEvents()
//    }
//
//    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
//        Toast.makeText(applicationContext, list[position], Toast.LENGTH_SHORT).show()
//    }
//
//    private fun setEvents() {
//        button.setOnClickListener({
//            text_input = editText.text.toString()
//            Toast.makeText(applicationContext, "Added " + text_input, Toast.LENGTH_LONG).show()
//
//            adapter.notifyDataSetChanged()
//            list.add(text_input)
//            editText.text.clear()
//        })
//
//        textClear.setOnClickListener({
//            list.clear()
//            adapter.notifyDataSetChanged()
//        })
//    }
//
//    private class CustomListView(context: Context, list: ArrayList<String>) : BaseAdapter() {
//        private var list: ArrayList<String> = ArrayList()
//        private var context = context;
//
//        init {
//            this.list = list
//            this.context = context
//        }
//
//        @SuppressLint("ViewHolder", "InflateParams")
//        override fun getView(position: Int, convertView: View?, ViewGroup: ViewGroup?): View {
//
//            var v: View
//            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            v = inflater.inflate(R.layout.custom_list_view, null)
//            val name = v.findViewById<TextView>(R.id.textView)
//            name.text = list[position]
//
//            val delete = v.findViewById<ImageView>(R.id.icon_delete)
//            delete.setOnClickListener({
//                list.removeAt(position)
//                this.notifyDataSetChanged()
//            })
//
//            return v
//        }
//
//        override fun getItem(p0: Int): Any {
//            return p0
//        }
//
//        override fun getItemId(p0: Int): Long {
//            return p0.toLong()
//        }
//
//        override fun getCount(): Int {
//            return list.size;
//        }
//    }
//}

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

typealias Arraylist<E> = java.util.ArrayList<E>

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var text_input: String
    private val list: Arraylist<String> = ArrayList()
    private lateinit var adapter: CustomListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = CustomListView(this, list)
        list_item.adapter = adapter
        list_item.onItemClickListener = this
        setEvents()
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        Toast.makeText(applicationContext, list[position], Toast.LENGTH_SHORT).show()
    }


    private fun setEvents() {
        button.setOnClickListener({
            text_input = editText.text.toString().trim()

            if (!text_input.equals("")) {
                Toast.makeText(applicationContext, "Added " + text_input, Toast.LENGTH_LONG).show()
                adapter.notifyDataSetChanged()
                list.add(text_input)
                editText.text.clear()
            }
        })

        //Chek process when you click on clear all text
        textClear.setOnClickListener({
            list.clear()
            adapter.notifyDataSetChanged()
        })

        //Check for your press the button
        editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perfor code
                Toast.makeText(applicationContext, "your press enter", Toast.LENGTH_LONG).show();
                button.performClick();
                return@OnKeyListener true
            }
            false
        })
    }

    private class CustomListView(context: Context, list: ArrayList<String>) : BaseAdapter() {

        private var list: ArrayList<String> = ArrayList()
        private var context: Context

        init {
            this.list = list
            this.context = context
        }

        @SuppressLint("ViewHolder", "InflateParams")
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            var v: View

            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = inflater.inflate(R.layout.custom_list_view, null)

            val name = v.findViewById(R.id.textView) as TextView
            name.text = list[position]

            val delete = v.findViewById(R.id.icon_delete) as ImageView
            delete.setOnClickListener({
                list.removeAt(position)
                this.notifyDataSetChanged()
            })

            return v
        }

        override fun getItem(p0: Int): Any {
            return p0
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return list.size
        }

    }

}