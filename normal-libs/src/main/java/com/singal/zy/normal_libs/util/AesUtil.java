package com.singal.zy.normal_libs.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Log;

/**
 * @category AES算法类，实现AES相关操作类
 */
public class AesUtil {
	private static final String TAG = "文件加密";

	// 构造方法，初始化具体参数
	public AesUtil() {

	}

	/**
	 * @param filepath
	 *            密钥生成后存放的文件路径
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static void keyGenerater(String filepath)
			throws NoSuchAlgorithmException, IOException {
		// 换取密钥生成器，初始化算法类型和模式
		KeyGenerator keyGen = KeyGenerator
				.getInstance("AES");
		// 初始化密钥，确定密钥长度以及随机种子等
		keyGen.init(56);
		// 根据初始化设置生成密钥
		SecretKey key = keyGen.generateKey();
		// 定义输出流，将密钥写入文件
		ObjectOutputStream oop = null;
		try {
			// 利用流打开文件
			oop = new ObjectOutputStream(new FileOutputStream(filepath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 将密钥写入文件
		oop.writeObject(key);
		// 关闭流
		oop.close();

	}

	// 文件加密操作，全局加密
	public static void all_encrypt(String epubfile, String desFile,
								   byte[] aeskey) throws Exception {
		// 读取文件
		Log.e(TAG, "加密文件名" + desFile);
		File file = new File(epubfile);
		// 创建输入流，读入加密文件
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				file), 64 * 1024);// 缓存128k
		File file1 = new File(desFile);
		// 建立文件输出流，创建buffer
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(file1), 64 * 1024);
		// 创建数组，用于存放文件流，文件流已经放入了buffer之中
		byte[] bytIn = new byte[512 * 1024];// 每次处理512k的数据
		while (bis.available() >= 512 * 1024) {
			// 将文件读入到字节
			bis.read(bytIn);
			// 写文件，通过密钥对明文字节数组进行加密
			byte[] bytOut = encryptData(bytIn, aeskey);
			// 输出流到文件
			bos.write(bytOut);
		}
		if (bis.available() > 0) // 尚有未处理的数据
		{
			byte[] bytIn_rest = new byte[(int) bis.available()];
			bis.read(bytIn_rest);
			byte[] bytOut_rest = encryptData(bytIn_rest, aeskey);
			bos.write(bytOut_rest);
			System.out.println("数据处理完毕");
		}
		// 刷空输出流
		bos.flush();
		// 关闭流
		bis.close();
		// 关闭流
		bos.close();

	}

	// 文件解密操作，全局解密
	public static void all_decrypt(String srcFile, String desFile, byte[] aeskey)
			throws Exception {
		// 打开目标文件
		File file = new File(srcFile);
		// 创建buffer，读取文件流
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				file), 64 * 1024);// 缓存64k
		// 创建或打开加密文件件路径
		File file1 = new File(desFile);
		// 建立文件输出流，创建buffer
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(file1), 64 * 1024);// 缓存64k
		// 创建数组，用于存放文件流，文件流已经放入了buffer之中
		byte[] bytIn = new byte[512 * 1024];// 每次处理512k的数据
		while (bis.available() >= 512 * 1024) {
			// 将文件读入到字节
			bis.read(bytIn);
			// 写文件，通过密钥对明文字节数组进行加密
			byte[] bytOut = decryptData(bytIn, aeskey);
			// 输出流到文件
			bos.write(bytOut);
		}
		if (bis.available() > 0) // 尚有未处理的数据
		{
			byte[] bytIn_rest = new byte[(int) bis.available()];
			bis.read(bytIn_rest);
			byte[] bytOut_rest = decryptData(bytIn_rest, aeskey);
			bos.write(bytOut_rest);
			System.out.println("数据处理完毕");
		}
		// 刷空输出流
		bos.flush();
		// 关闭流
		bis.close();
		// 关闭流
		bos.close();

	}

	/**
	 * AES加密算法实现
	 *
	 * @param input
	 *            待加密的明文字节数组
	 * @param key
	 *            密钥字节数组
	 * @return 密文字节数组
	 * @throws Exception
	 */
	private static byte[] encryptData(byte[] input, byte[] key)
			throws Exception {
		// 根据指定的256字节密钥数组生成AES加密密钥
		SecretKey aeskey = new SecretKeySpec(key, "AES");
		// 设置加密模式和填充方法
		Cipher c1 = Cipher.getInstance("AES/CFB/NoPadding");
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
		// 初始化AES加密算法
		c1.init(Cipher.ENCRYPT_MODE, aeskey, iv);
		// 执行加密操作
		byte[] clearByte = c1.doFinal(input);
		// 返回密文字节数组
		return clearByte;
	}

	/**
	 * AES解密算法实现
	 *
	 * @param input
	 *            密文字节数组
	 * @param key
	 *            密钥字节数组
	 * @return 明文字节数组
	 * @throws Exception
	 */
	private static byte[] decryptData(byte[] input, byte[] key)
			throws Exception {
		// 根据指定的256字节密钥生成AES加密密钥
		SecretKey aeskey = new SecretKeySpec(key, "AES");
		// 初始化AES加密算法，确定加密模式和填充方法
		Cipher c1 = Cipher.getInstance("AES/CFB/NoPadding");
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
		// 初始化参数
		c1.init(Cipher.DECRYPT_MODE, aeskey, iv);
		// 执行加密操作
		byte[] clearByte = c1.doFinal(input);
		// 返回明文数组
		return clearByte;
	}


	//数据加密
	public static byte[] data_encrypt(byte[] input,byte[] key)
			throws Exception{
		byte[]out=encryptData(input, key);
		return out;
	}
	//数据解密
	public static byte[] data_decrypt(byte[] input,byte[] key)
			throws Exception{
		byte[]out=decryptData(input, key);
		return out;
	}


	// 文件加密操作，部分加密，暂时只加密前2k
	public static void encrypt(String epubfile, String desFile, byte[] aeskey)
			throws Exception {
		// 读取文件
		Log.e(TAG, "加密文件名" + desFile);
		File file = new File(epubfile);
		// 创建输入流，读入加密文件
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				file), 64 * 1024);// 缓存128k
		File file1 = new File(desFile);
		// 建立文件输出流，创建buffer
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(file1), 64 * 1024);
		// 创建数组，用于存放文件流，文件流已经放入了buffer之中
		if (bis.available() > 2 * 1024) // 加密部分
		{
			byte[] bytIn = new byte[2 * 1024];
			bis.read(bytIn);
			byte[] bytOut = encryptData(bytIn, aeskey);
			bos.write(bytOut);
		}
		while (bis.available() >= 512 * 1024)// 每次处理512k数据,不加密
		{
			byte[] bytIn = new byte[512 * 1024];
			bis.read(bytIn);
			bos.write(bytIn);

		}
		if (bis.available() > 0) // 剩余部分数据
		{
			byte[] bytIn = new byte[(int) bis.available()];
			bis.read(bytIn);
			bos.write(bytIn);
		}
		// 刷空输出流
		bos.flush();
		// 关闭流
		bis.close();
		// 关闭流
		bos.close();

	}

	// 文件解密操作，部分解密，暂时只解密前2k
	public static void decrypt(String srcFile, String desFile, byte[] aeskey)
			throws Exception {
		// 打开目标文件
		File file = new File(srcFile);
		// 创建buffer，读取文件流
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				file), 64 * 1024);// 缓存64k
		// 创建或打开加密文件件路径
		File file1 = new File(desFile);
		// 建立文件输出流，创建buffer
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(file1), 64 * 1024);// 缓存64k
		if (bis.available() > 2 * 1024) // 解密部分
		{
			byte[] bytIn = new byte[2 * 1024];
			bis.read(bytIn);
			byte[] bytOut = decryptData(bytIn, aeskey);
			bos.write(bytOut);
		}
		while (bis.available() >= 512 * 1024)// 每次处理512k数据,不解密
		{
			byte[] bytIn = new byte[512 * 1024];
			bis.read(bytIn);
			bos.write(bytIn);

		}
		if (bis.available() > 0) // 剩余部分数据
		{
			byte[] bytIn = new byte[(int) bis.available()];
			bis.read(bytIn);
			bos.write(bytIn);
		}
		// 刷空输出流
		bos.flush();
		// 关闭流
		bis.close();
		// 关闭流
		bos.close();

	}
}