package com.data_structure.queue;

public class CircularIntQueue {
    int[] _queue;
    int _inPos;
    int _outPos;

    public CircularIntQueue(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Capacity can't less 0");
        }
        this._queue = new int[size];
        _inPos = _outPos = 0;
    }

    public boolean isEmpty() {
        return _inPos == _outPos;
    }

    public boolean push(int val) {
        int inPos = _inPos;
        int nextInPos = (inPos + 1) % _queue.length;
        if (nextInPos == _outPos) {
            return false;
        }

        _queue[inPos] = val;
        _inPos = nextInPos;
        return true;
    }

    public interface IValueComparator {
        int compare(int val1, int val2);
    }

    public boolean push(int val, IValueComparator cmp) {
        int inPos = _inPos;
        int outPos = _outPos;
        int nextInPos = (inPos + 1) % _queue.length;
        if (nextInPos == outPos) {
            return false;
        }

        while (outPos != inPos) {
            int outVal = _queue[outPos];
            int ret = cmp.compare(outVal, val);
            if (ret > 0) {
                _queue[outPos] = val;
                val = outVal;
            }
            ++outPos;
            outPos %= _queue.length;
        }

        _queue[inPos] = val;
        _inPos = nextInPos;
        return true;
    }

    public int peek() {
        int outPos = _outPos;
        if (outPos == _inPos) {
            return 0;
        }
        return _queue[outPos];
    }

    public int peek(int offset) {
        if (_outPos == _inPos) {
            return 0;
        }
        if (offset >= size()) {
            return 0;
        }
        int pos = (_outPos + offset);
        pos %= _queue.length;
        int val = _queue[pos];
        return val;
    }

    public int pull() {
        int outPos = _outPos;
        if (outPos == _inPos) {
            return 0;
        }
        int val = _queue[outPos];
        _queue[outPos] = 0;
        _outPos = (outPos + 1) % _queue.length;
        return val;
    }

    public int pull(int offset) {
        int outPos = _outPos;
        if (outPos == _inPos) {
            return 0;
        }
        int pos = (_outPos + offset);
        pos %= _queue.length;
        int val = _queue[pos];

        while (pos != _outPos) {
            int prevPos = pos - 1;
            if (prevPos < 0) {
                prevPos += _queue.length;
            }
            _queue[pos] = _queue[prevPos];
            pos = prevPos;
        }
        //_queue[pos] = 0;
        _outPos = (outPos + 1) % _queue.length;
        return val;
    }

    public int size() {
        return (_inPos - _outPos + _queue.length) % _queue.length;
    }

    public int capacity() {
        return _queue.length - 1;
    }

    public void clear() {
        _inPos = _outPos = 0;
    }
}
