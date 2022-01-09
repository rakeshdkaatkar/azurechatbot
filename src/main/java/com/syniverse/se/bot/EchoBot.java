// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.syniverse.se.bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.bot.builder.ActivityHandler;
import com.microsoft.bot.builder.MessageFactory;
import com.microsoft.bot.builder.TurnContext;
import com.microsoft.bot.schema.Activity;
import com.microsoft.bot.schema.ChannelAccount;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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


    @Override
    protected CompletableFuture<Void> onMessageActivity(TurnContext turnContext) {
        LOGGER.info("\n");
        LOGGER.info(">>>> NEW onMessageActivity event");
        LOGGER.info("activityId:{}", turnContext.getActivity().getType());
        LOGGER.info("Conversation:{}", turnContext.getActivity().getConversation().toString());
        LOGGER.info("channelId:{}", turnContext.getActivity().getChannelId());
        LOGGER.info("recipientId:{}", turnContext.getActivity().getRecipient().getId());
        LOGGER.info("recipientName:{}", turnContext.getActivity().getRecipient().getName());
        LOGGER.info("from:{}", turnContext.getActivity().getFrom());
        LOGGER.info("text: {}", turnContext.getActivity().getText());
        LOGGER.info("response from bot: Echo: {}", turnContext.getActivity().getText());
        String echoMsg;
        if (turnContext.getActivity().getAttachments() != null && turnContext.getActivity().getAttachments().size() > 0) {

            try {
                echoMsg = "Echo: " + new ObjectMapper().writeValueAsString(turnContext.getActivity().getAttachments().get(0));
                return turnContext.sendActivity(
                        MessageFactory.text(new ObjectMapper().writeValueAsString(turnContext.getActivity().getAttachments().get(0)))
                ).thenApply(sendResult -> null);
            } catch (JsonProcessingException e) {
                return turnContext.sendActivity(
                        MessageFactory.text("Echo:" + turnContext.getActivity().getText())
                ).thenApply(sendResult -> null);
            }
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
        //LOGGER.info(">>> Replying with message: {}",echoMsg);

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
