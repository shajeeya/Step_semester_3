
package sorting_algorithms.class_problems;

import java.util.Arrays;

public class Problem5 {

    interface Resource {
        String getId();
    }

    interface Reservable {
        void reserve(String memberId);
    }

    interface Downloadable {
        void download();
    }

    static class Book implements Resource, Reservable {
        private final String id;

        public Book(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public void reserve(String memberId) {
            System.out.println(id + " reserved for " + memberId);
        }
    }

    static class EBook implements Resource, Downloadable {
        private final String id;

        public EBook(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public void download() {
            System.out.println(id + " downloaded");
        }
    }

    static class LibraryPlatform {
        private Resource[] resources = new Resource[10];
        private int size = 0;

        public void addResource(Resource resource) {
            int position = findResource(resource.getId());

            if (position != -1) {
                System.out.println("duplicate rejected");
                return;
            }

            int insertAt = 0;

            while (insertAt < size &&
                   resources[insertAt].getId().compareTo(resource.getId()) < 0) {
                insertAt++;
            }

            if (size == resources.length) {
                resources = Arrays.copyOf(resources, size * 2);
            }

            for (int i = size; i > insertAt; i--) {
                resources[i] = resources[i - 1];
            }

            resources[insertAt] = resource;
            size++;

            System.out.println(resource.getId() + " added");
        }

        public int findResource(String id) {
            int left = 0;
            int right = size - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int comparison = resources[mid].getId().compareTo(id);

                if (comparison == 0) {
                    return mid;
                } else if (comparison < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }

        public void reserve(String id, String memberId) {
            int index = findResource(id);

            if (index == -1) {
                System.out.println(id + " not found");
                return;
            }

            Resource resource = resources[index];

            if (resource instanceof Reservable) {
                ((Reservable) resource).reserve(memberId);
            } else {
                System.out.println(id + " rejected: reservation unsupported");
            }
        }

        public void download(String id) {
            int index = findResource(id);

            if (index == -1) {
                System.out.println(id + " not found");
                return;
            }

            Resource resource = resources[index];

            if (resource instanceof Downloadable) {
                ((Downloadable) resource).download();
            } else {
                System.out.println(id + " rejected: download unsupported");
            }
        }

        public void displayResources() {
            for (int i = 0; i < size; i++) {
                System.out.println(i + ": " + resources[i].getId());
            }
        }
    }

    public static void main(String[] args) {
        LibraryPlatform library = new LibraryPlatform();

        library.addResource(new Book("B1"));
        library.addResource(new Book("B1"));
        library.addResource(new EBook("E1"));

        library.reserve("B1", "M1");
        library.download("B1");
        library.download("E1");

        int index = library.findResource("E1");

        if (index != -1) {
            System.out.println("E1 found at index " + index);
        } else {
            System.out.println("E1 not found");
        }
    }
}
