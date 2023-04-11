package org.nhn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;


/**
 *
 * 클래스.
 */
public class LinkedList<E> implements List<E>, Cloneable {

    Node<E> head;	// 노드의 첫 부분
    Node<E> tail;	// 노드의 마지막 부분
    int size;	// 요소 개수


    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 6. 정해진 위치의 노드를 반환합니다.
    /**
     * getEntry.
     *
     * @param index index
     * @return return
     */
    private Node<E> getEntry(int index) {
        if ( ( index < 0 ) || ( index >= size ) )
            throw new IndexOutOfBoundsException();

        // 뒤에서부터 검색
        if (index + 1 > size / 2) {
            Node<E> x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        } else {
            Node<E> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }
    }

    public void addFirst(E newEntry) {
        Node<E> newNode = new Node<E>(newEntry);	// 새 노드 생성
        newNode.next = head;

        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        size++;

        if (head.next == null) {
            tail = head;
        }

    }

    @Override
    public boolean add(E newEntry) {
        addLast(newEntry);
        return true;
    }


    public void addLast(E newEntry) {
        Node<E> newNode = new Node<E>(newEntry);

        if (size == 0) {
            addFirst(newEntry);
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }


    // 2. 정해진 위치에 새 개체를 추가합니다
    public void remove(int newPosition, E newEntry) {

        // 잘못된 인덱스를 참조할 경우 예외 발생
        if (newPosition > size || newPosition < 0) {
            throw new IndexOutOfBoundsException();
        }
        // 추가하려는 index가 가장 앞에 추가하려는 경우 addFirst 호출
        if (newPosition == 0) {
            addFirst(newEntry);
            return;
        }
        // 추가하려는 index가 마지막 위치일 경우 addLast 호출
        if (newPosition == size) {
            addLast(newEntry);
            return;
        }

        // 추가하려는 위치의 이전 노드
        Node<E> prevNode = getEntry(newPosition - 1);

        // 추가하려는 위치의 노드
        Node<E> nextNode = prevNode.next;

        // 추가하려는 노드
        Node<E> newNode = new Node<E>(newEntry);

        // 링크 끊기
        prevNode.next = null;
        nextNode.prev = null;

        // 링크 연결하기
        prevNode.next = newNode;

        newNode.prev = prevNode;
        newNode.next = nextNode;

        nextNode.prev = newNode;
        size++;
    }



    //3. 정해진 위치의 개체를 삭제합니다.
    public E remove() {

        Node<E> headNode = head;

        if (headNode == null) {
            throw new NoSuchElementException();
        }

        // 삭제된 노드를 반환하기 위한 임시 변수
        E element = headNode.data;

        // head의 다음 노드
        Node<E> nextNode = head.next;

        // head 노드의 데이터들을 모두 삭제
        head.data = null;
        head.next = null;


        if(nextNode != null) {
            nextNode.prev = null;
        }

        head = nextNode;
        size--;

        if(size == 0) {
            tail = null;
        }

        return element;
    }


    @Override
    public E remove(int position) {

        if (position >= size || position < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (position == 0) {
            E element = head.data;
            remove();
            return element;
        }

        Node<E> prevNode = getEntry(position - 1);	// 삭제할 노드의 이전 노드
        Node<E> removedNode = prevNode.next;	// 삭제할 노드
        Node<E> nextNode = removedNode.next;	// 삭제할 노드의 다음 노드

        E element = removedNode.data;	// 삭제되는 노드의 데이터를 반환하기 위한 임시변수

        prevNode.next = null;
        removedNode.next = null;
        removedNode.prev = null;
        removedNode.data = null;

        if(nextNode != null) {
            nextNode.prev = null;

            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        }

        /**
         *  nextNode가 null이라는 것은 마지막 노드를 삭제했다는 의미이므로
         *  prevNode가 tail이 된다. (연결 해줄 것이 없음)
         */
        else {
            tail = prevNode;
        }

        size--;

        return element;
    }

    // 4. 모든 노드를 삭제합니다.
    @Override
    public void clear() {
        for (Node<E> x = head; x != null;) {
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = nextNode;
        }
        head = tail = null;
        size = 0;
    }

    // 5. 정해진 위치의 개체를 파라미터 인자로 전달받은 개채로 변경합니다.
    public void replace(int newPosition, E newEntry) {

        Node<E> prev = getEntry(newPosition);
        prev.data = null;
        prev.data = newEntry;
    }

    // 7. 컬렉션의 크기(노드 수)를 반환합니다.
    @Override
    public int size() {
        return size;
    }


    // 8. 노드의 값 기준으로 오름차순 정렬합니다.
    public Object[] toArray() {
        Object[] array = new Object[size];
        int idx = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            array[idx++] = x.data;
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // Arrya.newInstance(컴포넌트 타입, 생성할 크기)
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node<E> x = head; x != null; x = x.next) {
            result[i++] = x.data;
        }

        return a;
    }

    public void sort() {
        /**
         *  Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된
         *  정렬 방식을 사용한다.
         *  만약 구현되어있지 않으면 cannot be cast to class java.lang.Comparable
         *  에러가 발생한다.
         *  만약 구현되어있을 경우 null로 파라미터를 넘기면
         *  Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
         */
        sort(null);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);

        int i = 0;
        for (Node<E> x = head; x != null; x = x.next, i++) {
            x.data = (E) a[i];
        }
    }

    @Override
    public void add(int index, E value) {
    }

    @Override
    public boolean remove(Object value) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E value) {
    }

    @Override
    public boolean contains(Object value) {
        return false;
    }

    @Override
    public int indexOf(Object value) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}


class Node<E> {

    E data;
    Node<E> next;
    Node<E> prev;

    Node(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

}

interface List<E> {

    boolean add(E value);

    void add(int index, E value);

    E remove(int index);

    boolean remove(Object value);

    E get(int index);

    void set(int index, E value);

    boolean contains(Object value);

    int indexOf(Object value);

    int size();

    boolean isEmpty();

    public void clear();

}