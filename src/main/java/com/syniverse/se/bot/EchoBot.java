// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.syniverse.se.bot;

import com.microsoft.bot.builder.ActivityHandler;
import com.microsoft.bot.builder.MessageFactory;
import com.microsoft.bot.builder.TurnContext;
import com.microsoft.bot.schema.Activity;
import com.microsoft.bot.schema.Attachment;
import com.microsoft.bot.schema.ChannelAccount;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This class implements the functionality of the Bot.
 *
 * <p>
 * This is where application specific logic for interacting with the users would be added. For this
 * sample, the {@link #onMessageActivity(TurnContext)} echos the text back to the user. The {@link
 * #onMembersAdded(List, TurnContext)} will send a greeting to new conversation participants.
 * </p>
 */
public class EchoBot extends ActivityHandler {
    private static final Logger LOGGER = LogManager.getLogger(EchoBot.class.getName());

    private static int DELAY = 2000;

    @Override
    protected CompletableFuture<Void> onMessageActivity(TurnContext turnContext) {
        LOGGER.info("\n");
        LOGGER.info(">>>> NEW onMessageActivity event");
        LOGGER.info("activityId:{}", turnContext.getActivity().getId());
        LOGGER.info("channelData:{}", turnContext.getActivity().getChannelData());
        LOGGER.info("Conversation:{}", turnContext.getActivity().getConversation().toString());
        LOGGER.info("channelId:{}", turnContext.getActivity().getChannelId());
        LOGGER.info("recipientId:{}", turnContext.getActivity().getRecipient().getId());
        LOGGER.info("recipientName:{}", turnContext.getActivity().getRecipient().getName());
        LOGGER.info("from:{}", turnContext.getActivity().getFrom());
        LOGGER.info("text: {}", turnContext.getActivity().getText());
        LOGGER.info("response from bot: Echo: {}", turnContext.getActivity().getText());
        try {


            if (turnContext.getActivity().getText() != null && turnContext.getActivity().getText().contains("@unilever_test")) {
                 String delay[] = turnContext.getActivity().getText().split(":");
                 if(delay!=null && delay.length >=2){
                     DELAY = Integer.valueOf(delay[1]);
                 }
                turnContext.sendActivity(
                        MessageFactory.text("Test Message")
                ).thenApply(sendResult -> null);

                Thread.sleep(DELAY);

                List<Attachment> attachmentList = new ArrayList<>();

                Attachment attachment = new Attachment();
                attachment.setName("Test_Img.jpeg");
                attachment.setContentUrl("https://azcbnepasstorageuat.blob.core.windows.net/images-demo/Test_Img.jpeg");
                attachment.setContentType("image/jpeg");
                attachmentList.add(attachment);
                turnContext.sendActivity(
                        //turnContext.getActivity()
                        MessageFactory.attachment(attachmentList, "Sample Text", "", null)
                        //MessageFactory.attachment(aa)
                ).thenApply(sendResult -> null);
                Thread.sleep(DELAY);

                turnContext.sendActivity(
                        MessageFactory.text("Test Message")
                ).thenApply(sendResult -> null);


                Thread.sleep(DELAY);
                return turnContext.sendActivity(
                        MessageFactory.text("#parse(\"whatsapp:ns=8b24c6b8_f5f2_268f_acec_5acebd0277dc:name=button_sample:lang=en_US\")#set(Rakesh)#set($quick_reply=YES)#set($quick_reply=NO)")
                ).thenApply(sendResult -> null);
            }
            if (turnContext.getActivity().getText() != null && turnContext.getActivity().getText().contains("@image_test")) {
                List<Attachment> attachmentList = new ArrayList<>();

                Attachment attachment = new Attachment();
                attachment.setName("Test_Img.jpeg");
                attachment.setContentUrl("https://azcbnepasstorageuat.blob.core.windows.net/images-demo/Test_Img.jpeg");
                attachment.setContentType("image/jpeg");
                attachmentList.add(attachment);
                turnContext.sendActivity(
                        //turnContext.getActivity()
                        MessageFactory.attachment(attachmentList, "Sample Text", "", null)
                        //MessageFactory.attachment(aa)
                ).thenApply(sendResult -> null);

                Thread.sleep(DELAY);

                return turnContext.sendActivity(
                        MessageFactory.text("Test Message After Attachment")
                ).thenApply(sendResult -> null);
            }

            if (turnContext.getActivity().getText() != null && turnContext.getActivity().getText().contains("@text_test")) {
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Test Message 1")
                ).thenApply(sendResult -> null);
                LOGGER.info("1");
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Test Message 2")
                ).thenApply(sendResult -> null);
                LOGGER.info("2");
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Test Message 3")
                ).thenApply(sendResult -> null);
                LOGGER.info("3");
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Test Message 4")
                ).thenApply(sendResult -> null);
                LOGGER.info("4");
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Test Message 5")
                ).thenApply(sendResult -> null);
                LOGGER.info("5");
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Test Message 6")
                ).thenApply(sendResult -> null);
                LOGGER.info("6");
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Test Message 7")
                ).thenApply(sendResult -> null);
                LOGGER.info("7");
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Test Message 8")
                ).thenApply(sendResult -> null);
                LOGGER.info("8");

            } else if (turnContext.getActivity().getText() != null && turnContext.getActivity().getText().contains("@emoji_test")) {
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Emoji Test 1 \uD83D\uDE4B\uD83C\uDFFC\u200D♀")
                ).thenApply(sendResult -> null);
                LOGGER.info("1");
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Emoji Test 2 \uD83D\uDE4B\uD83C\uDFFC\u200D♀")
                ).thenApply(sendResult -> null);
                LOGGER.info("2");
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Emoji Test 3 \uD83D\uDE4B\uD83C\uDFFC\u200D♀")
                ).thenApply(sendResult -> null);
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Emoji Test 4 \uD83D\uDE4B\uD83C\uDFFC\u200D♀")
                ).thenApply(sendResult -> null);
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Emoji Test 5 \uD83D\uDE4B\uD83C\uDFFC\u200D♀")
                ).thenApply(sendResult -> null);
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Emoji Test 6 \uD83D\uDE4B\uD83C\uDFFC\u200D♀")
                ).thenApply(sendResult -> null);
                LOGGER.info("6");
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Emoji Test 7 \uD83D\uDE4B\uD83C\uDFFC\u200D♀")
                ).thenApply(sendResult -> null);
                Thread.sleep(DELAY);
                turnContext.sendActivity(
                        MessageFactory.text("Emoji Test  8 \uD83D\uDE4B\uD83C\uDFFC\u200D♀")
                ).thenApply(sendResult -> null);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String echoMsg;
        if (turnContext.getActivity().getAttachments() != null && turnContext.getActivity().getAttachments().size() > 0) {
            List<Attachment> attachmentList = new ArrayList<>();
            Attachment attachment = new Attachment();
            attachment.setContentUrl(turnContext.getActivity().getAttachments().get(0).getContentUrl());
            attachment.setContentType(turnContext.getActivity().getAttachments().get(0).getContentType());
            attachment.setName(turnContext.getActivity().getAttachments().get(0).getName());
            attachmentList.add(attachment);


            //echoMsg = "Echo: " + new ObjectMapper().writeValueAsString(turnContext.getActivity().getAttachments().get(0));
            //LOGGER.info(">>> Replying with message: {}",echoMsg + turnContext.getActivity().getType());
            return turnContext.sendActivity(
                    //turnContext.getActivity()
                    MessageFactory.attachment(attachmentList, turnContext.getActivity().getText(), "", null)
                    //MessageFactory.attachment(aa)
            ).thenApply(sendResult -> null);

        } else if (turnContext.getActivity().getText() != null) {
            echoMsg = "Echo: " + turnContext.getActivity().getText();
            LOGGER.info(">>> Replying with message: {}", echoMsg);
            return turnContext.sendActivity(
                    MessageFactory.text("Echo:" + turnContext.getActivity().getText())
            ).thenApply(sendResult -> null);
        } else {
            return turnContext.sendActivity(
                    MessageFactory.text(" Something Went wrong")
            ).thenApply(sendResult -> null);
        }

        //turnContext.getActivity().getAttachments().get(0).getName();
        //String echoMsg = "Echo: " + turnContext.getActivity().getText() + turnContext.getActivity().getAttachments().get(0).getName();

    }

    @Override
    protected CompletableFuture<Void> onMembersAdded(
            List<ChannelAccount> membersAdded,
            TurnContext turnContext
    ) {
        LOGGER.info("\n");
        LOGGER.info(">>>> NEW onMembersAdded event");
        LOGGER.info("channelId:{}", turnContext.getActivity().getChannelId());
        LOGGER.info("from:{}", turnContext.getActivity().getFrom());
        LOGGER.info("recipientId:{}", turnContext.getActivity().getRecipient().getId());
        LOGGER.info("recipientName:{}", turnContext.getActivity().getRecipient().getName());
        //LOGGER.info("Sending initial welcome: Hello and welcome!");

        membersAdded.forEach(m -> LOGGER.info("onMembersAdded: membersAdded=(id={},name={},role={}",
                m.getId(),
                m.getName(),
                m.getRole()));

        return super.onMembersAdded(membersAdded, turnContext);
        /*
        return membersAdded.stream()
            .filter(
                member -> !StringUtils
                    .equals(member.getId(), turnContext.getActivity().getRecipient().getId())
            ).map(channel -> turnContext.sendActivity(MessageFactory.text("Hello and welcome!")))
            .collect(CompletableFutures.toFutureList()).thenApply(resourceResponses -> null);

         */
    }

    @Override
    protected CompletableFuture<Void> onConversationUpdateActivity(TurnContext turnContext) {
        Activity activity = turnContext.getActivity();
        LOGGER.info("\n");
        LOGGER.info(">>>> NEW onConversationUpdateActivity event, activity.getRecipient().getId()={}", activity.getRecipient().getId());

        if (activity.getMembersAdded() != null &&
                activity.getRecipient() != null &&
                activity.getMembersAdded().stream().anyMatch((m) -> {
                    return !StringUtils.equals(m.getId(), activity.getRecipient().getId());
                })) {
            return this.onMembersAdded(activity.getMembersAdded(), turnContext);
        } else {
            return activity.getMembersRemoved() != null &&
                    activity.getRecipient() != null &&
                    activity.getMembersRemoved().stream().anyMatch((m) -> {
                        return !StringUtils.equals(m.getId(), activity.getRecipient().getId());
                    }) ? this.onMembersRemoved(activity.getMembersRemoved(), turnContext) : CompletableFuture.completedFuture((Void) null);
        }
    }

    @Override
    protected CompletableFuture<Void> onMessageReactionActivity(TurnContext turnContext) {
        LOGGER.info("\n");
        LOGGER.info(">>>> NEW onMessageReactionActivity event");
        LOGGER.info("onMessageReactionActivity: channelId:{}", turnContext.getActivity().getChannelId());
        LOGGER.info("onMessageReactionActivity: recipientId:{}", turnContext.getActivity().getRecipient().getId());
        LOGGER.info("onMessageReactionActivity: recipientName:{}", turnContext.getActivity().getRecipient().getName());
        LOGGER.info("onMessageReactionActivity: from:{}", turnContext.getActivity().getFrom());
        LOGGER.info("onMessageReactionActivity: text:{}", turnContext.getActivity().getText());
        return super.onMessageReactionActivity(turnContext);
    }

    @Override
    protected CompletableFuture<Void> onEventActivity(TurnContext turnContext) {
        LOGGER.info("\n");
        LOGGER.info(">>>> NEW onEventActivity event");
        LOGGER.info("onEventActivity: channelId:{}", turnContext.getActivity().getChannelId());
        LOGGER.info("onEventActivity: recipientId:{}", turnContext.getActivity().getRecipient().getId());
        LOGGER.info("onEventActivity: recipientName:{}", turnContext.getActivity().getRecipient().getName());
        LOGGER.info("onEventActivity: from:{}", turnContext.getActivity().getFrom());
        LOGGER.info("onEventActivity: text:{}", turnContext.getActivity().getText());
        return super.onEventActivity(turnContext);
    }
}
