package by.tc.task33.entity;

import java.util.Objects;

public class Page {
	private int begin;
	private int end;
	private int first;
	private int last;
	private int size;
	private int page;
	private int prev;
	private int next;

	public Page() {
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Page page1 = (Page) o;
		return begin == page1.begin &&
				end == page1.end &&
				first == page1.first &&
				last == page1.last &&
				size == page1.size &&
				page == page1.page &&
				prev == page1.prev &&
				next == page1.next;
	}

	@Override
	public int hashCode() {
		return Objects.hash(begin, end, first, last, size, page, prev, next);
	}

	@Override
	public String toString() {
		return "Page{" +
				"begin=" + begin +
				", end=" + end +
				", first=" + first +
				", last=" + last +
				", size=" + size +
				", page=" + page +
				", prev=" + prev +
				", next=" + next +
				'}';
	}
}
