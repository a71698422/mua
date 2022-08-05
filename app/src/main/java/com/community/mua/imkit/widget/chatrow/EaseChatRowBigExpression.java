package com.community.mua.imkit.widget.chatrow;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.community.mua.R;
import com.community.mua.imkit.EaseIM;
import com.community.mua.imkit.constants.EaseConstant;
import com.community.mua.imkit.domain.EaseEmojicon;
import com.hyphenate.chat.EMMessage;


/**
 * big emoji icons
 *
 */
public class EaseChatRowBigExpression extends EaseChatRowText {
    private ImageView imageView;

    public EaseChatRowBigExpression(Context context, boolean isSender) {
        super(context, isSender);
    }

    public EaseChatRowBigExpression(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(!showSenderType ? R.layout.ease_row_received_bigexpression
                : R.layout.ease_row_sent_bigexpression, this);
    }

    @Override
    protected void onFindViewById() {
        percentageView = (TextView) findViewById(R.id.percentage);
        imageView = (ImageView) findViewById(R.id.image);
    }

    @Override
    public void onSetUpView() {
        String emojiconId = message.getStringAttribute(EaseConstant.MESSAGE_ATTR_EXPRESSION_ID, null);
        EaseEmojicon emojicon = null;
        if(EaseIM.getInstance().getEmojiconInfoProvider() != null){
            emojicon =  EaseIM.getInstance().getEmojiconInfoProvider().getEmojiconInfo(emojiconId);
        }
        if(emojicon != null){
            if(emojicon.getBigIcon() != 0){

                Glide.with(context).load(emojicon.getBigIcon())
                        .apply(RequestOptions.placeholderOf(R.drawable.ease_default_expression))
                        .into(imageView);
            }else if(emojicon.getBigIconPath() != null){
                Glide.with(context).load(emojicon.getBigIconPath())
                        .apply(RequestOptions.placeholderOf(R.drawable.ease_default_expression))
                        .into(imageView);
            }else{
                imageView.setImageResource(R.drawable.ease_default_expression);
            }
        }
    }
}