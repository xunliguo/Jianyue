package com.example.asus_pc.jianyue.utils;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;


import com.example.asus_pc.jianyue.global.MyApp;
import com.example.asus_pc.jianyue.pojo.Musics;

import org.androidannotations.annotations.App;

import java.util.ArrayList;

/**
 * 2015年8月15日 16:34:37
 * 博文地址：http://blog.csdn.net/u010156024
 */
public class LocalMusicUtils {
	/**
	 * 根据id获取歌曲uri
	 * @deprecated
	 * @param musicId
	 * @return
	 */
	public static String queryMusicById(int musicId) {
		String result = null;
		Cursor cursor = MyApp.sContext.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaStore.Audio.Media.DATA },
				MediaStore.Audio.Media._ID + "=?",
				new String[] { String.valueOf(musicId) }, null);

		for (cursor.moveToFirst(); !cursor.isAfterLast();) {
			result = cursor.getString(0);
			break;
		}

		cursor.close();
		return result;
	}

	/**
	 * 获取目录下的歌曲
	 * @param dirName
	 */
	public static ArrayList<Musics> queryMusic(String dirName) {
		ArrayList<Musics> results = new ArrayList<>();
		Cursor cursor = MyApp.sContext.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null,
				MediaStore.Audio.Media.DATA + " like ?",
				new String[] { dirName + "%" },
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		if(cursor == null){
			cursor = MyApp.sContext.getContentResolver().query(
					MediaStore.Audio.Media.INTERNAL_CONTENT_URI, null,
					MediaStore.Audio.Media.DATA + " like ?",
					new String[] { dirName + "%" },
					MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
			if(cursor==null)
				return results;
		}
		
		// id title singer data time image
		addMedia(cursor, results);
		
		cursor = MyApp.sContext.getContentResolver().query(
				MediaStore.Audio.Media.INTERNAL_CONTENT_URI, null,
				MediaStore.Audio.Media.DATA + " like ?",
				new String[] { dirName + "%" },
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		if(cursor==null)
			return results;
		addMedia(cursor, results);
		return results;
	}
	
	private static void addMedia(Cursor cursor,ArrayList<Musics> results){
		Musics music;
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			// 如果不是音乐
			String isMusic = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.IS_MUSIC));
			if (isMusic != null && isMusic.equals("")) continue;
			
			String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
			String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
			
			if(isRepeat(title, artist)) continue;
			
			music = new Musics();
			music.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)));
			music.setTitle(title);
			music.setArtist(artist);
			music.setUri(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
			music.setLength(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
			music.setImage(getAlbumImage(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID))));
			results.add(music);
		}

		cursor.close();
	}
	
	/**
	 * 根据音乐名称和艺术家来判断是否重复包含了
	 * @param title
	 * @param artist
	 * @return
	 */
	private static boolean isRepeat(String title, String artist) {
		for(Musics music : MusicUtils.sMusicList) {
			if(title.equals(music.getTitle()) && artist.equals(music.getArtist())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据歌曲id获取图片
	 * @param albumId
	 * @return
	 */
	private static String getAlbumImage(int albumId) {
		String result = "";
		Cursor cursor = null;
		try {
			cursor = MyApp.sContext.getContentResolver().query(
					Uri.parse("content://media/external/audio/albums/"
							+ albumId), new String[] { "album_art" }, null,
					null, null);
			for (cursor.moveToFirst(); !cursor.isAfterLast();) {
				result = cursor.getString(0);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != cursor) {
				cursor.close();
			}
		}
		return null == result ? null : result;
	}
}
