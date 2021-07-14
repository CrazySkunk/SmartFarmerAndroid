package com.code.jamie.smartfarm.activities.homepage.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.adapters.NotificationAdapter
import com.code.jamie.smartfarm.databinding.FragmentNotificationBinding
import com.code.jamie.smartfarm.models.NotificationItem
import com.code.jamie.smartfarm.utils.Utilities
import com.google.android.material.switchmaterial.SwitchMaterial

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NotificationFragment : Fragment() {
    lateinit var binding: FragmentNotificationBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        val notificationList = ArrayList<NotificationItem>()
        var timeStr: String? = ""
        var dateStr: String = ""
        notificationList.add(
            NotificationItem(
                "Buy inoculation",
                "Go to Haile salassie ave and look for a shop that sells inoculation",
                "10:00 AM",
                true
            )
        )
        notificationList.add(
            NotificationItem(
                "Buy inoculation",
                "Go to Haile salassie ave and look for a shop that sells inoculation",
                "10:00 AM",
                true
            )
        )
        notificationList.add(
            NotificationItem(
                "Buy inoculation",
                "Go to Haile salassie ave and look for a shop that sells inoculation",
                "10:00 AM",
                false
            )
        )
        notificationList.add(
            NotificationItem(
                "Buy inoculation",
                "Go to Haile salassie ave and look for a shop that sells inoculation",
                "10:00 AM",
                false
            )
        )
        notificationList.add(
            NotificationItem(
                "Buy inoculation",
                "Go to Haile salassie ave and look for a shop that sells inoculation",
                "10:00 AM",
                true
            )
        )
        notificationList.add(
            NotificationItem(
                "Buy inoculation",
                "Go to Haile salassie ave and look for a shop that sells inoculation",
                "10:00 AM",
                false
            )
        )
        notificationList.add(
            NotificationItem(
                "Buy inoculation",
                "Go to Haile salassie ave and look for a shop that sells inoculation",
                "10:00 AM",
                true
            )
        )
        notificationList.add(
            NotificationItem(
                "Buy inoculation",
                "Go to Haile salassie ave and look for a shop that sells inoculation",
                "10:00 AM",
                false
            )
        )
        val notifAdapter = NotificationAdapter(notificationList, null)
        binding.notificationRecyclerView.apply {
            hasFixedSize()
            clipToPadding = false
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = notifAdapter
        }
        notifAdapter.setOnItemClickListener(object : NotificationAdapter.OnItemClick {
            override fun onItemClick(position: Int) {
                Toast.makeText(
                    requireContext(),
                    "Item at position ${notificationList[position]} was clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        binding.addNotificationFab.setOnClickListener {
            val layout = layoutInflater.inflate(R.layout.create_notification_dialog, null)
            val title: EditText = layout.findViewById(R.id.title_notif_dialog)
            val description: EditText = layout.findViewById(R.id.description_notif_dialog)
            val notify: ImageView = layout.findViewById(R.id.bell_notif_dialog)
            val notifySwitch: SwitchMaterial = layout.findViewById(R.id.switch_notif_dialog)
            val addBtn: Button = layout.findViewById(R.id.save_button_notif_dialog)
            val cancelBtn: Button = layout.findViewById(R.id.cancel_button_notif_dialog)
            val timeEt: EditText = layout.findViewById(R.id.time_et_notif_dialog)
            val dateEt: EditText = layout.findViewById(R.id.date_et_notif_dialog)
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setCancelable(true)
            alertDialog.setView(layout)
            val alert = alertDialog.create()
            timeEt.setOnFocusChangeListener { v, hasFocus ->
                if (v.id == R.id.time_et_notif_dialog && hasFocus) {
                    run {
                        val hour = Calendar.HOUR_OF_DAY
                        val minute1 = Calendar.MINUTE
                        val timePicker =
                            TimePickerDialog(requireContext(), { view, hourOfDay, minute ->
                                timeStr = "$hourOfDay : $minute"
                                view.currentMinute = minute1
                                view.currentHour = hour
                                view.setIs24HourView(true)
                            }, hour, minute1, true)
                        timePicker.create()
                        timePicker.show()
//                timeEt.text = timeStr
                    }
                }
            }

            dateEt.setOnFocusChangeListener { v1, hasFocus1 ->
                if (v1.id == R.id.date_et_notif_dialog && hasFocus1) {
                    run {
                        val day = Calendar.DAY_OF_MONTH
                        val month1 = Calendar.MONTH
                        val year1 = Calendar.YEAR
                        val datePicker =
                            DatePickerDialog(requireContext(), { view, year, month, dayOfMonth ->
                                dateStr = "$dayOfMonth/$month/$year"
                                view.maxDate = System.currentTimeMillis()
                            }, year1, month1, day)
                        datePicker.create()
                        datePicker.show()
                    }
//            dateEt.text = dateStr
                }
            }


            addBtn.setOnClickListener {
                val titleStr = title.text.toString().trim()
                val descriptionStr = description.text.toString().trim()
                val notifyStr = notifySwitch.isChecked
                if (notifyStr) notify.visibility = View.VISIBLE else notify.visibility =
                    View.INVISIBLE
                if (titleStr.isEmpty()) {
                    Utilities().shortToast(requireContext(), "At least provide a title")
                } else {
                    //todo: add notification to dialog
                    Utilities().shortToast(
                        requireContext(),
                        "Notification added with $titleStr\n$descriptionStr\n$timeStr\n$dateStr"
                    )
                    alert.dismiss()
                }

            }
            cancelBtn.setOnClickListener {
                alert.dismiss()
            }
            alert.show()
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotificationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    //todo : IMPORTANT !!! push code to cloud first thing you committed before you slept
    //todo: name on the edit profile misbehaving probably the others too check on them an also the other alert dialogs
}