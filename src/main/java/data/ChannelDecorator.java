package data;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.IShard;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.AttachmentPartEntry;
import sx.blah.discord.util.Image;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MessageHistory;
import sx.blah.discord.util.cache.LongMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Instant;
import java.util.EnumSet;
import java.util.List;

public class ChannelDecorator implements IChannel {

    private IChannel channel;

    public ChannelDecorator(IChannel channel){
        this.channel = channel;
    }

    @Override
    public String getName() {
        return channel.getName();
    }

    @Override
    public MessageHistory getMessageHistory() {
        return channel.getMessageHistory();
    }

    @Override
    public MessageHistory getMessageHistory(int messageCount) {
        return channel.getMessageHistory(messageCount);
    }

    @Override
    public MessageHistory getMessageHistoryFrom(Instant startDate) {
        return channel.getMessageHistoryFrom(startDate);
    }

    @Override
    public MessageHistory getMessageHistoryFrom(Instant startDate, int maxMessageCount) {
        return channel.getMessageHistoryFrom(startDate, maxMessageCount);
    }

    @Override
    public MessageHistory getMessageHistoryFrom(long id) {
        return channel.getMessageHistoryFrom(id);
    }

    @Override
    public MessageHistory getMessageHistoryFrom(long id, int maxMessageCount) {
        return channel.getMessageHistoryFrom(id, maxMessageCount);
    }

    @Override
    public MessageHistory getMessageHistoryTo(Instant endDate) {
        return channel.getMessageHistoryTo(endDate);
    }

    @Override
    public MessageHistory getMessageHistoryTo(Instant endDate, int maxMessageCount) {
        return channel.getMessageHistoryTo(endDate, maxMessageCount);
    }

    @Override
    public MessageHistory getMessageHistoryTo(long id) {
        return channel.getMessageHistoryTo(id);
    }

    @Override
    public MessageHistory getMessageHistoryTo(long id, int maxMessageCount) {
        return channel.getMessageHistoryTo(id, maxMessageCount);
    }

    @Override
    public MessageHistory getMessageHistoryIn(Instant startDate, Instant endDate) {
        return channel.getMessageHistoryIn(startDate, endDate);
    }

    @Override
    public MessageHistory getMessageHistoryIn(Instant startDate, Instant endDate, int maxMessageCount) {
        return channel.getMessageHistoryIn(startDate, endDate, maxMessageCount);
    }

    @Override
    public MessageHistory getMessageHistoryIn(long beginID, long endID) {
        return channel.getMessageHistoryIn(beginID, endID);
    }

    @Override
    public MessageHistory getMessageHistoryIn(long beginID, long endID, int maxMessageCount) {
        return channel.getMessageHistoryIn(beginID, endID, maxMessageCount);
    }

    @Override
    public MessageHistory getFullMessageHistory() {
        return channel.getFullMessageHistory();
    }

    @Override
    public List<IMessage> bulkDelete() {
        return channel.bulkDelete();
    }

    @Override
    public List<IMessage> bulkDelete(List<IMessage> messages) {
        return channel.bulkDelete(messages);
    }

    @Override
    public int getMaxInternalCacheCount() {
        return channel.getMaxInternalCacheCount();
    }

    @Override
    public int getInternalCacheCount() {
        return channel.getInternalCacheCount();
    }

    @Override
    public IMessage getMessageByID(long messageID) {
        return channel.getMessageByID(messageID);
    }

    @Override
    public IMessage fetchMessage(long messageID) {
        return channel.fetchMessage(messageID);
    }

    @Override
    public IGuild getGuild() {
        return channel.getGuild();
    }

    @Override
    public boolean isPrivate() {
        return channel.isPrivate();
    }

    @Override
    public boolean isNSFW() {
        return channel.isNSFW();
    }

    @Override
    public String getTopic() {
        return channel.getTopic();
    }

    @Override
    public String mention() {
        return channel.mention();
    }

    @Override
    public IMessage sendMessage(String content) {
        return channel.sendMessage(content);
    }

    @Override
    public IMessage sendMessage(EmbedObject embed) {
        return channel.sendMessage(embed);
    }

    @Override
    public IMessage sendMessage(String content, boolean tts) {
        return channel.sendMessage(content, tts);
    }

    @Override
    public IMessage sendMessage(String content, EmbedObject embed) {
        return channel.sendMessage(content, embed);
    }

    @Override
    public IMessage sendMessage(String content, EmbedObject embed, boolean tts) {
        return channel.sendMessage(content, embed, tts);
    }

    @Override
    public IMessage sendFile(File file) throws FileNotFoundException {
        return channel.sendFile(file);
    }

    @Override
    public IMessage sendFiles(File... files) throws FileNotFoundException {
        return channel.sendFiles(files);
    }

    @Override
    public IMessage sendFile(String content, File file) throws FileNotFoundException {
        return channel.sendFile(content, file);
    }

    @Override
    public IMessage sendFiles(String content, File... files) throws FileNotFoundException {
        return channel.sendFiles(content, files);
    }

    @Override
    public IMessage sendFile(EmbedObject embed, File file) throws FileNotFoundException {
        return channel.sendFile(embed, file);
    }

    @Override
    public IMessage sendFiles(EmbedObject embed, File... files) throws FileNotFoundException {
        return channel.sendFiles(embed, files);
    }

    @Override
    public IMessage sendFile(String content, InputStream file, String fileName) {
        return channel.sendFile(content, file, fileName);
    }

    @Override
    public IMessage sendFiles(String content, AttachmentPartEntry... entries) {
        return channel.sendFiles(content, entries);
    }

    @Override
    public IMessage sendFile(EmbedObject embed, InputStream file, String fileName) {
        return channel.sendFile(embed, file, fileName);
    }

    @Override
    public IMessage sendFiles(EmbedObject embed, AttachmentPartEntry... entries) {
        return channel.sendFiles(embed, entries);
    }

    @Override
    public IMessage sendFile(String content, boolean tts, InputStream file, String fileName) {
        return channel.sendFile(content, tts, file, fileName);
    }

    @Override
    public IMessage sendFiles(String content, boolean tts, AttachmentPartEntry... entries) {
        return channel.sendFiles(content, tts, entries);
    }

    @Override
    public IMessage sendFile(String content, boolean tts, InputStream file, String fileName, EmbedObject embed) {
        return channel.sendFile(content, tts, file, fileName, embed);
    }

    @Override
    public IMessage sendFiles(String content, boolean tts, EmbedObject embed, AttachmentPartEntry... entries) {
        return channel.sendFiles(content, tts, embed, entries);
    }

    @Override
    public IMessage sendFile(MessageBuilder builder, InputStream file, String fileName) {
        return channel.sendFile(builder, file, fileName);
    }

    @Override
    public IInvite createInvite(int maxAge, int maxUses, boolean temporary, boolean unique) {
        return channel.createInvite(maxAge, maxUses, temporary, unique);
    }

    @Override
    public void toggleTypingStatus() {
        channel.toggleTypingStatus();
    }

    @Override
    public void setTypingStatus(boolean typing) {
        channel.setTypingStatus(typing);
    }

    @Override
    public boolean getTypingStatus() {
        return channel.getTypingStatus();
    }

    @Override
    public void edit(String name, int position, String topic) {
        channel.edit(name, position, topic);
    }

    @Override
    public void changeName(String name) {
        channel.changeName(name);
    }

    @Override
    public void changePosition(int position) {
        channel.changePosition(position);
    }

    @Override
    public void changeTopic(String topic) {
        channel.changeTopic(topic);
    }

    @Override
    public void changeNSFW(boolean isNSFW) {
        channel.changeNSFW(isNSFW);
    }

    @Override
    public int getPosition() {
        return channel.getPosition();
    }

    @Override
    public void delete() {
        channel.delete();
    }

    @Override
    public LongMap<PermissionOverride> getUserOverrides() {
        return channel.getUserOverrides();
    }

    @Override
    public LongMap<PermissionOverride> getRoleOverrides() {
        return channel.getRoleOverrides();
    }

    @Override
    public EnumSet<Permissions> getModifiedPermissions(IUser user) {
        return channel.getModifiedPermissions(user);
    }

    @Override
    public EnumSet<Permissions> getModifiedPermissions(IRole role) {
        return channel.getModifiedPermissions(role);
    }

    @Override
    public void removePermissionsOverride(IUser user) {
        channel.removePermissionsOverride(user);
    }

    @Override
    public void removePermissionsOverride(IRole role) {
        channel.removePermissionsOverride(role);
    }

    @Override
    public void overrideRolePermissions(IRole role, EnumSet<Permissions> toAdd, EnumSet<Permissions> toRemove) {
        channel.overrideRolePermissions(role, toAdd, toRemove);
    }

    @Override
    public void overrideUserPermissions(IUser user, EnumSet<Permissions> toAdd, EnumSet<Permissions> toRemove) {
        channel.overrideUserPermissions(user, toAdd, toRemove);
    }

    @Override
    public List<IExtendedInvite> getExtendedInvites() {
        return channel.getExtendedInvites();
    }

    @Override
    public List<IUser> getUsersHere() {
        return channel.getUsersHere();
    }

    @Override
    public List<IMessage> getPinnedMessages() {
        return channel.getPinnedMessages();
    }

    @Override
    public void pin(IMessage message) {
        channel.pin(message);
    }

    @Override
    public void unpin(IMessage message) {
        channel.unpin(message);
    }

    @Override
    public List<IWebhook> getWebhooks() {
        return channel.getWebhooks();
    }

    @Override
    public IWebhook getWebhookByID(long id) {
        return channel.getWebhookByID(id);
    }

    @Override
    public List<IWebhook> getWebhooksByName(String name) {
        return channel.getWebhooksByName(name);
    }

    @Override
    public IWebhook createWebhook(String name) {
        return channel.createWebhook(name);
    }

    @Override
    public IWebhook createWebhook(String name, Image avatar) {
        return channel.createWebhook(name, avatar);
    }

    @Override
    public IWebhook createWebhook(String name, String avatar) {
        return channel.createWebhook(name, avatar);
    }

    @Override
    public boolean isDeleted() {
        return channel.isDeleted();
    }

    @Override
    public void changeCategory(ICategory category) {
        channel.changeCategory(category);
    }

    @Override
    public ICategory getCategory() {
        return channel.getCategory();
    }

    @Override
    public IDiscordClient getClient() {
        return channel.getClient();
    }

    @Override
    public IShard getShard() {
        return channel.getShard();
    }

    @Override
    public IChannel copy() {
        return channel.copy();
    }

    @Override
    public long getLongID() {
        return channel.getLongID();
    }

    @Override
    public String toString(){
        return getStringID() + " - " + getName();
    }
}
