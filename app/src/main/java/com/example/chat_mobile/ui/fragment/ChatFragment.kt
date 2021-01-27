package com.example.chat_mobile.ui.fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.chat_mobile.R
import com.example.chat_mobile.databinding.ItemChatReceiveBinding
import com.example.chat_mobile.databinding.ItemChatSendBinding
import com.example.chat_mobile.payload.Message
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatFragment : Fragment() {
    private val messageAdapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var sendButton: Button
    private lateinit var content: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        chat_recycler_view.adapter = messageAdapter
        populateData()
        initViews(requireView())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    private fun initViews(view: View) {
        sendButton = view.findViewById(R.id.chat_send_message)
        content = view.findViewById(R.id.chat_message_input_area)
        initListeners()
    }

    private fun populateData() {
        val data = listOf<Message>()
        data.forEach {
            if (it.sender == "me") {
                messageAdapter.add(SendMessageItem(it))
            } else {
                messageAdapter.add(ReceiveMessageItem(it))
            }
        }
    }

    private fun initListeners() {
        sendButton.setOnClickListener {
            val message = Message(sendButton.text.toString(), "me")
            val sendMessageItem = SendMessageItem(message)

            messageAdapter.add(sendMessageItem)
            content.text.clear()

            receiveAutoResponse()

        }
    }
    private fun receiveAutoResponse() {
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            val receive = Message("roma mushaobs", "me")
            val receiveItem = ReceiveMessageItem(receive)

            messageAdapter.add(receiveItem)
        }
    }
}

class SendMessageItem(private val message: Message) : BindableItem<ItemChatSendBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_chat_send
    }

    override fun bind(viewBinding: ItemChatSendBinding, position: Int) {
        viewBinding.message = message
    }
}

class ReceiveMessageItem(private val message: Message) : BindableItem<ItemChatReceiveBinding>() {
    override fun getLayout(): Int {
        return R.layout.item_chat_receive
    }

    override fun bind(viewBinding: ItemChatReceiveBinding, position: Int) {
        viewBinding.message = message
    }
}