package com.idig8.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.idig8.myapplication.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView mListView;
    List<ChatMessage> mChatMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mListView = findViewById(R.id.chartlistview);

        ChatMessage chatMessage = new ChatMessage(1,2,"刘小明","8:20","你好吗","","",true);
        ChatMessage chatMessage2 = new ChatMessage(2,1,"小军","8:21","我很好","","",false);
        ChatMessage chatMessage3 = new ChatMessage(1,2,"刘小明","8:22","今天天气怎么样","","",true);
        ChatMessage chatMessage4 = new ChatMessage(2,1,"小军","8:23","热成狗了","","",false);

        mChatMessages.add(chatMessage);
        mChatMessages.add(chatMessage2);
        mChatMessages.add(chatMessage3);
        mChatMessages.add(chatMessage4);

        mListView.setAdapter(new ChatMessageAdapter(this, mChatMessages));

    }

    public static class ChatMessageAdapter extends BaseAdapter{

        public interface IMessageViewType {
            int COM_MESSAGE = 0;
            int TO_MESSAGE = 1;
        }
        List<ChatMessage> chatMessageList;
        Context mContext;

        public ChatMessageAdapter(Context context,List<ChatMessage> chatMessageList) {
            mContext = context;
            this.chatMessageList = chatMessageList;
        }

        @Override
        public int getCount() {
            return chatMessageList.size();
        }

        @Override
        public Object getItem(int position) {
            return chatMessageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            ChatMessage entity = chatMessageList.get(position);
            if (entity.getMsgType()) {
                return IMessageViewType.COM_MESSAGE;
            } else {
                return IMessageViewType.TO_MESSAGE;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ChatMessage entity = chatMessageList.get(position);
            boolean isComMsg = entity.getMsgType();

            ViewHolder viewHolder;
            if (convertView == null) {
                if (isComMsg) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.chatting_item_msg_text_left, null);
                } else {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.chatting_item_msg_text_right, null);
                }
                viewHolder = new ViewHolder();
                viewHolder.mSendTime = (TextView) convertView.findViewById(R.id.tv_send_time);
                viewHolder.mUserName = (TextView) convertView.findViewById(R.id.tv_username);
                viewHolder.mContent = (TextView) convertView.findViewById(R.id.tv_chat_content);
                viewHolder.mTime = (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.mUserAvatar = (ImageView) convertView.findViewById(R.id.iv_user_head);
                viewHolder.mIsComMessage = isComMsg;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.mSendTime.setText(entity.getDate());
            viewHolder.mContent.setText(entity.getContent());
            viewHolder.mContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            viewHolder.mTime.setText("");
            viewHolder.mUserName.setText(entity.getName());
            if (isComMsg) {
                viewHolder.mUserAvatar.setImageResource(R.drawable.avatar);
            } else {
                viewHolder.mUserAvatar.setImageResource(R.mipmap.ic_launcher);
//                ImageLoader.getInstance().displayImage(entity.getAvatarUrl(), viewHolder.mUserAvatar);
            }

            return convertView;
        }

        class ViewHolder {
            public TextView mSendTime;
            public TextView mUserName;
            public TextView mContent;
            public TextView mTime;
            public ImageView mUserAvatar;
            public boolean mIsComMessage = true;
        }
    }
}
