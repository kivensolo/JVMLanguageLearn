package com.string.replace;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by hy on 2015/5/12.
 */
public class LineReader {
	public static final String CHARSET_NAME = "UTF-8";

	InputStream _is;
	boolean _dropFirstLF = false;

	ArrayList<String> _lines = new ArrayList<String>();

	ByteArrayOutputStream _w = new ByteArrayOutputStream();

	public LineReader(InputStream s) {
		_is = s;
	}

	private void _prepareLines() {
		try {
			while (_is.available() > 0) {
				int ch = _is.read();
				if (ch == -1) {
					break;
				}
				while (true) {
					switch (ch) {
					case '\r':
						if (_is.available() > 0) {
							ch = _is.read();
							if (ch == -1) {
								_dropFirstLF = true;
							} else if (ch == '\n') {
							} else {
								_dropFirstLF = false;
								continue;
							}
						} else {
							_dropFirstLF = true;
						}
						_lines.add(_w.toString(CHARSET_NAME));
						_w.reset();
						break;
					case '\n':
						if (_dropFirstLF) {
							break;
						}
						_lines.add(_w.toString(CHARSET_NAME));
						_w.reset();
						break;
					default:
						_w.write(ch);
						break;
					}
					break;
				}
				_dropFirstLF = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean ready() {
		_prepareLines();
		return !_lines.isEmpty();
	}

	public String readLine() {
		return _lines.isEmpty() ? null : _lines.remove(0);
	}

	public void flush() {
		if (_w.size()>0) {
			try {
				_lines.add(_w.toString(CHARSET_NAME));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			_w.reset();
		}
	}
}
